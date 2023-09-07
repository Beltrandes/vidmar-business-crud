package vidmarbusiness.crud.vidmarbusiness.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "obra")
public class Work {

    @Id
    @JsonProperty("_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(length = 80, name = "nome")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @Column(length = 80, name = "endereço")
    private String address;

    @NotNull
    @Pattern(regexp = "Marmoraria|Vidraçaria|Ambos")
    private String serviceType;

    @NotNull
    @Column(length = 50, name = "data_inicio")
    private LocalDate initialDate;

    @Column(length = 50, name = "data_termino")
    private LocalDate finishDate;






}
