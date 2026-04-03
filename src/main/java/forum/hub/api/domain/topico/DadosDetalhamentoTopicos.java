package forum.hub.api.domain.topico;

import forum.hub.api.domain.curso.DadosListagemCurso;
import forum.hub.api.domain.resposta.DadosListagemResposta;
import forum.hub.api.domain.usuario.DadosListagemUsuario;

import java.time.LocalDate;
import java.util.List;

public record DadosDetalhamentoTopicos(
        Long id,
        String titulo,
        String mensagem,
        LocalDate dataCriacao,
        Status status,
        DadosListagemUsuario autor,
        DadosListagemCurso curso,
        List<DadosListagemResposta> respostas) {

    public DadosDetalhamentoTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), new DadosListagemUsuario(topico.getAutor()), new DadosListagemCurso(topico.getCurso()), topico.getRespostas().stream().map(DadosListagemResposta::new).toList());
    }
}
