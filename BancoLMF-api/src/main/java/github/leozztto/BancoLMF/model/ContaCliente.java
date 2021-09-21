package github.leozztto.BancoLMF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class ContaCliente {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "descricao", nullable = false, length = 100)
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    @NotNull(message = "{campo.clienteSaldo.obrigatorio}")
    private Cliente cliente;

    @Column(name = "conta", length = 50, nullable = false)
    @NotNull(message = "{campo.conta.obrigatorio}")
    private Long conta;

    @Column(name = "username", length = 100, nullable = false)
    @NotNull(message = "{campo.username.obrigatorio}")
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    @NotNull(message = "{campo.password.obrigatorio}")
    private String password;

    @Column(name = "saldoInicial")
    private Double saldoInicial;

    @ManyToOne
    @JoinColumn(name = "idTipoConta")
    @NotNull(message = "{campo.tipoConta.obrigatorio}")
    private TipoConta tipoConta;

    @Column(name = "dataCadastro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataCadastro.obrigatorio}")
    private LocalDate dataCadastro;

    @Column(name = "dataAlteracao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataAlteracao.obrigatorio}")
    private LocalDate dataAlteracao;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
