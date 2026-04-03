package forum.hub.api.controller;

import forum.hub.api.domain.resposta.DadosCadastroResposta;
import forum.hub.api.domain.resposta.Resposta;
import forum.hub.api.domain.resposta.RespostaRepository;
import forum.hub.api.domain.topico.Status;
import forum.hub.api.domain.topico.TopicoRepository;
import forum.hub.api.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos/{topicoId}/respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {
    @Autowired
    private RespostaRepository respostaRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Usuario usuarioLogado, @PathVariable Long topicoId){
        var topico = topicoRepository.getReferenceById(topicoId);
        var resposta = new Resposta(dados, topico, usuarioLogado);
        topico.adicionarResposta(resposta);
        respostaRepository.save(resposta);

        var uri = uriBuilder.path("topicos/{topicoId}/respostas/{respostaId}").buildAndExpand(topico.getId(), resposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
