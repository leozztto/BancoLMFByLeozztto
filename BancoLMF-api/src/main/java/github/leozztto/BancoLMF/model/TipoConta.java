package github.leozztto.BancoLMF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class TipoConta {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    //pode ser corrente, poupança, estudante
    @Column(name = "descricao", nullable = false, length=50)
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @Column(name = "codigoOperacao", nullable = false, length=3)
    @NotEmpty(message = "{campo.codigoOperacao.obrigatorio}")
    private String codigoOperacao;

    @Column(name = "dataCadastro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataCadastro.obrigatorio}")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
