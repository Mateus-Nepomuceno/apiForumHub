package forum.hub.api.domain.resposta;

import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.infra.exception.TopicoInvalido;
import forum.hub.api.infra.exception.UsuarioInvalido;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {
    public void validaTopico(Resposta resposta, Long topicoId){
        if (!resposta.getTopico().getId().equals(topicoId)){
            throw new TopicoInvalido("A resposta não pertence ao tópico.");
        }
    }

    public void validaUsuario(Resposta resposta, Usuario usuario){
        if (!resposta.getAutor().equals(usuario)){
            throw new UsuarioInvalido("O usuario não é autor da resposta");
        }
    }
}
