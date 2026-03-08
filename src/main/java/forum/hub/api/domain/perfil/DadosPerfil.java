package forum.hub.api.domain.perfil;

import jakarta.validation.constraints.NotNull;

public record DadosPerfil(
        @NotNull
        Tipo nome) {
}
