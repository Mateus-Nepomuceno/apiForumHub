package forum.hub.api.controller;

import forum.hub.api.domain.perfil.PerfilRepository;
import forum.hub.api.domain.perfil.Tipo;
import forum.hub.api.domain.usuario.DadosCadastroUsuario;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        var tipoPerfil = dados.perfil().nome();
        var perfil = perfilRepository.findByNome(tipoPerfil);
        usuario.adicionarPerfil(perfil);
        usuarioRepository.save(usuario);
        var uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
