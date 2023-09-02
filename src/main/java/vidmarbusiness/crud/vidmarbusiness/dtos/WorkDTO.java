package vidmarbusiness.crud.vidmarbusiness.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;

import java.time.LocalDate;

public record WorkDTO(
        Long id,
        String name,

        Cliente cliente,
        String address,
        @NotNull @NotBlank @Pattern(regexp = "Marmoraria|Vidraçaria|Ambos") String serviceType,

        LocalDate initialDate,

        LocalDate finishDate
) {
}
