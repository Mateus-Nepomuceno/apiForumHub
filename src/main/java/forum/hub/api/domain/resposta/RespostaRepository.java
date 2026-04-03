package forum.hub.api.domain.resposta;

import forum.hub.api.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    Page<Resposta> findAllByTopico(Topico topico, Pageable paginacao);
}