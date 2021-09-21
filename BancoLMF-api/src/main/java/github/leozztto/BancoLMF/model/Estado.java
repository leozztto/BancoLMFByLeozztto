package github.leozztto.BancoLMF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "nome", nullable = false, length=50)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "sigla", nullable = false, length=2)
    @NotEmpty(message = "{campo.sigla.obrigatorio}")
    private String sigla;

    @Column(name = "dataCadastro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataCadastro.obrigatorio}")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
