package forum.hub.api.infra.exception;

public class TopicoInvalido extends RuntimeException {
    public TopicoInvalido(String message) {
        super(message);
    }
}
