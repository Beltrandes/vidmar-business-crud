package vidmarbusiness.crud.vidmarbusiness.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @JsonProperty("_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(length = 80)
    private String name;

    @Column(length = 80)
    private String address;



    @NotNull
    @NotBlank
    @Column(length = 20)
    private String number;

    @NotNull
    @OneToMany(mappedBy = "cliente")
    private List<Work> works;
}
