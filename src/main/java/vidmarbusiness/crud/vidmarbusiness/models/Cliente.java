package vidmarbusiness.crud.vidmarbusiness.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    @JsonIgnore
    private List<Work> works = new ArrayList<>();

    public void setWorks(List<Work> newWorks) {
        works.clear();

        for (Work work : newWorks) {
            work.setCliente(this);
            works.add(work);
        }
    }

    private String arrowDirection = "down";
}
