package forum.hub.api.domain.usuario;

import forum.hub.api.domain.perfil.DadosPerfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotNull
        @Valid
        DadosPerfil perfil) {
}
