package forum.hub.api.controller;

import forum.hub.api.domain.perfil.PerfilRepository;
import forum.hub.api.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    @SecurityRequirements()
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        var tipoPerfil = dados.perfil().nome();
        var perfil = perfilRepository.findByNome(tipoPerfil);
        usuario.adicionarPerfil(perfil);
        usuarioRepository.save(usuario);
        var uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listagem(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var dados = usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarUsuario dados, @PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }

}
