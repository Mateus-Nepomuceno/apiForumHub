package forum.hub.api.controller;

import forum.hub.api.domain.curso.CursoRepository;
import forum.hub.api.domain.topico.DadosCadastroTopico;
import forum.hub.api.domain.topico.DadosListagemTopicos;
import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.topico.TopicoRepository;
import forum.hub.api.domain.usuario.UsuarioRepository;
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
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var autor = usuarioRepository.getReferenceById(dados.autorId());
        var curso = cursoRepository.getReferenceById(dados.cursoId());

        var topico = new Topico(dados, autor, curso);
        topicoRepository.save(topico);
        var uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopicos>> listar(@PageableDefault(size = 10, sort = "dataCriacao") Pageable paginacao){
        var dados = topicoRepository.findAll(paginacao).map(DadosListagemTopicos::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTopicos(topico));
    }
}
