package forum.hub.api.domain.topico;

import java.time.LocalDate;

public record DadosListagemTopicos(
        Long id,
        String titulo,
        String mensagem,
        LocalDate dataCriacao,
        Status status) {

    public DadosListagemTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus());
    }
}
