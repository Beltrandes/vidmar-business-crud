package vidmarbusiness.crud.vidmarbusiness.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;

import java.time.LocalDate;

public record WorkDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Length(max = 50) String name,
        @NotNull @NotBlank @Length(max = 50) Cliente cliente,
        @NotNull @NotBlank @Length(max = 50) String address,
        @NotNull @Length(max = 20) LocalDate initialDate,
        @NotNull @Length(max = 20) LocalDate finishDate
) {
}
