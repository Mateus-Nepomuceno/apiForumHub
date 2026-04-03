package forum.hub.api.domain.topico;

import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.infra.exception.UsuarioInvalido;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    public void validaUsuario(Topico topico, Usuario usuario){
        if (!topico.getAutor().equals(usuario)){
            throw new UsuarioInvalido("O usuario não é autor do tópico");
        }
    }
}
