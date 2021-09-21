package github.leozztto.BancoLMF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class TipoOperacao {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    //Pode ser transferência, depósito, saque
    @Column(name = "descricao", nullable = false, length = 100)
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @Column(name = "dataCadastro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataCadastro.obrigatorio}")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
