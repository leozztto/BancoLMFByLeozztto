package github.leozztto.BancoLMF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Cliente {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "nome", nullable = false, length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(name = "cnpj", length = 14)
    @NotNull(message = "{campo.cnpj.obrigatorio}")
    @CNPJ(message = "{campo.cnpj.invalido}")
    private String cnpj;

    @Column(name = "tipoPessoa", length = 1)
    @NotNull(message = "{campo.tipopessoa.obrigatorio}")
    private String tipoPessoa;

    @Column(name = "dataNasc")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataNasc.obrigatorio}")
    private LocalDate dataNasc;

    @Column(name = "dataCriacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataCriacao.obrigatorio}")
    private LocalDate dataCriacao;

    @Column(name = "genero", length = 1)
    private String genero;

    @Column(name = "cepEnd", nullable = false, length = 8)
    @NotEmpty(message = "{campo.cepEnd.obrigatorio}")
    private String cepEnd;

    @Column(name = "ruaEnd", nullable = false, length = 150)
    @NotEmpty(message = "{campo.ruaEnd.obrigatorio}")
    private String ruaEnd;

    @Column(name = "numeroEnd", nullable = false, length = 10)
    @NotEmpty(message = "{campo.numeroEnd.obrigatorio}")
    private String numeroEnd;

    @Column(name = "bairroEnd", nullable = false, length = 100)
    @NotEmpty(message = "{campo.bairroEnd.obrigatorio}")
    private String bairroEnd;

    @Column(name = "complEnd", nullable = false, length = 200)
    @NotEmpty(message = "{campo.complEnd.obrigatorio}")
    private String complEnd;

    @ManyToOne
    @JoinColumn(name = "idCidadeEnd")
    @NotNull(message = "{campo.cidadeEnd.obrigatorio}")
    private Cidade cidadeEnd;

    @Column(name = "telefone", nullable = false, length = 11)
    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    private String telefone;

    @Column(name = "celular", nullable = false, length = 11)
    @NotEmpty(message = "{campo.celular.obrigatorio}")
    private String celular;

    @Column(name = "email", nullable = false, length = 150)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

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

