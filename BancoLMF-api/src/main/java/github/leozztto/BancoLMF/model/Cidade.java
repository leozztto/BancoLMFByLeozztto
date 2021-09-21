package github.leozztto.BancoLMF.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "nome", nullable = false, length = 50)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idEstado")
    @NotNull(message = "{campo.estado.obrigatorio}")
    private Estado estado;

    @Column(name = "dataCadastro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{campo.dataCadastro.obrigatorio}")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
