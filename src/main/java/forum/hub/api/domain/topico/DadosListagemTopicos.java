package forum.hub.api.domain.topico;

import forum.hub.api.domain.curso.Curso;
import forum.hub.api.domain.curso.DadosListagemCurso;
import forum.hub.api.domain.usuario.DadosListagemUsuario;
import forum.hub.api.domain.usuario.Usuario;

import java.time.LocalDate;

public record DadosListagemTopicos(
        Long id,
        String titulo,
        String mensagem,
        LocalDate dataCriacao,
        Status status,
        DadosListagemUsuario autor,
        DadosListagemCurso curso) {

    public DadosListagemTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), new DadosListagemUsuario(topico.getAutor()), new DadosListagemCurso(topico.getCurso()));
    }
}
