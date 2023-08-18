package vidmarbusiness.crud.vidmarbusiness.models;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "itens-estoque")
public class Item {
    @Id
    @JsonProperty("_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 1000)
    private Integer amount;

    @NotBlank
    @NotNull
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "Marmoraria|Vidraçaria")
    @Column(length = 20, nullable = false)
    private String type;

}
