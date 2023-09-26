package vidmarbusiness.crud.vidmarbusiness.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;

import java.time.LocalDate;
import java.util.List;

public record OrcamentoDTO(
        @JsonProperty("_id") Long id,
        @NotNull Cliente cliente,
        @NotNull LocalDate data,
        @NotNull String contato,
        @NotNull String descricao,

        @NotNull @Pattern(regexp = "Não Fechado|Fechado") String status,
        String arquivo,
        @NotNull Double total

        ) {
}
