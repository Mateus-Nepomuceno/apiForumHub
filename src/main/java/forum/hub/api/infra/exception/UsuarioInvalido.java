package forum.hub.api.infra.exception;

public class UsuarioInvalido extends RuntimeException {
    public UsuarioInvalido(String message) {
        super(message);
    }
}
