package forum.hub.api.domain.resposta;

import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Resposta")
@Table(name = "respostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToOne
    private Topico topico;
    private LocalDate dataCriacao;
    @ManyToOne
    private Usuario autor;
    private Boolean solucao;

    public Resposta(@Valid DadosCadastroResposta dados,Topico topico, Usuario usuarioLogado) {
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDate.now();
        this.autor = usuarioLogado;
        this.solucao = false;
        this.topico = topico;
    }

    public void atualizar(DadosAtualizarResposta dados) {
        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
    }
}
