package forum.hub.api.domain.resposta;

import forum.hub.api.domain.usuario.DadosListagemUsuario;

import java.time.LocalDate;

public record DadosListagemResposta(
        String mensagem,
        LocalDate dataCriacao,
        DadosListagemUsuario autor,
        Boolean solucao) {
    public DadosListagemResposta(Resposta resposta){
        this(resposta.getMensagem(), resposta.getDataCriacao(), new DadosListagemUsuario(resposta.getAutor()), resposta.getSolucao());
    }
}
