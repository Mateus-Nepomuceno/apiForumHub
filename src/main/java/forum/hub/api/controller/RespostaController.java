package forum.hub.api.controller;

import forum.hub.api.domain.resposta.*;
import forum.hub.api.domain.topico.TopicoRepository;
import forum.hub.api.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    private RespostaService respostaService;

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

    @GetMapping
    public ResponseEntity<Page<DadosListagemResposta>> listar(@PageableDefault(size = 10, sort = "dataCriacao") Pageable paginacao, @PathVariable Long topicoId){
        var topico = topicoRepository.getReferenceById(topicoId);
        var dados = respostaRepository.findAllByTopico(topico,paginacao).map(DadosListagemResposta::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long topicoId, @PathVariable Long id){
        var resposta = respostaRepository.getReferenceById(id);
        respostaService.validaTopico(resposta, topicoId);
        return ResponseEntity.ok(new DadosDetalharResposta(resposta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long topicoId, @PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado){
        var resposta = respostaRepository.getReferenceById(id);
        respostaService.validaTopico(resposta, topicoId);
        respostaService.validaUsuario(resposta, usuarioLogado);
        respostaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizarResposta dados, @PathVariable Long topicoId, @PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado){
        var resposta = respostaRepository.getReferenceById(id);
        respostaService.validaTopico(resposta, topicoId);
        respostaService.validaUsuario(resposta, usuarioLogado);
        resposta.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalharResposta(resposta));
    }
}
