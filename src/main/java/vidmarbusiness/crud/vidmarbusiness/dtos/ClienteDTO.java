package vidmarbusiness.crud.vidmarbusiness.dtos;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vidmarbusiness.crud.vidmarbusiness.models.Work;

import java.util.List;

public record ClienteDTO(
    @JsonProperty("_id") Long id,
    @NotNull @NotBlank @Length(max = 50) String name,
    @Length(max = 50) String address,
    @NotNull @NotBlank @Length(max = 50) List<Work> works,
    @NotNull @NotBlank @Length(max = 20) String number
) {
    
}
