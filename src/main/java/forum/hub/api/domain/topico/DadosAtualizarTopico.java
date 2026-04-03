package forum.hub.api.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopico(
        String titulo,
        String mensagem,
        @NotNull
        Long usuarioId) {
}
