package vidmarbusiness.crud.vidmarbusiness.dtos;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ItemDTO(
    @JsonProperty("_id") Long id,
    @NotNull Integer amount,
    @NotNull @NotBlank @Length(max = 100) String name,
    @NotNull @NotBlank @Length(max = 20) @Pattern(regexp = "Marmoraria|Vidraçaria") String type
) {
    
}
