package forum.hub.api.domain.resposta;

import forum.hub.api.domain.usuario.DadosListagemUsuario;

import java.time.LocalDate;

public record DadosDetalharResposta(
        Long id,
        String mensagem,
        LocalDate dataCriacao,
        DadosListagemUsuario autor,
        Boolean solucao) {
    public DadosDetalharResposta(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), new DadosListagemUsuario(resposta.getAutor()), resposta.getSolucao());
    }
}
