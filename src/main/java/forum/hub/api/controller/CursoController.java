package forum.hub.api.controller;

import forum.hub.api.domain.curso.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder){
        var curso = new Curso(dados);
        repository.save(curso);

        var uri = uriBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var dados = repository.findAll(paginacao).map(DadosListagemCurso::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var curso = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarCurso dados,@PathVariable Long id){
        var curso = repository.getReferenceById(id);
        curso.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
