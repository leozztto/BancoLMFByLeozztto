package github.leozztto.BancoLMF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class MovimentoOperacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "descricao", nullable = false, length = 100)
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "idContaOrigem")
    @NotNull(message = "{campo.contaOrigem.obrigatorio}")
    private ContaCliente contaOrigem;

    @OneToOne
    @JoinColumn(name = "idContaDestino")
    @NotNull(message = "{campo.contaDestino.obrigatorio}")
    private ContaCliente contaDestino;

    @Column(name = "valorOperacao")
    private Double valorOperacao;

    @Column(name = "dataMovimentoOperacao", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataMovimentoOperacao.obrigatorio}")
    private LocalDate dataMovimento;

    @Column(name = "observacao", length = 1000)
    private String observacao;

    @PrePersist
    public void prePersist(){
        setDataMovimento(LocalDate.now());
    }
}
