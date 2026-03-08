package forum.hub.api.domain.topico;

import forum.hub.api.domain.curso.DadosListagemCurso;
import forum.hub.api.domain.usuario.DadosListagemUsuario;

import java.time.LocalDate;

public record DadosDetalhamentoTopicos(
        Long id,
        String titulo,
        String mensagem,
        LocalDate dataCriacao,
        Status status,
        DadosListagemUsuario autor,
        DadosListagemCurso curso) {

    public DadosDetalhamentoTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), new DadosListagemUsuario(topico.getAutor()), new DadosListagemCurso(topico.getCurso()));
    }
}
