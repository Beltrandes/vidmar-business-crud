package vidmarbusiness.crud.vidmarbusiness.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;
import vidmarbusiness.crud.vidmarbusiness.models.ItemOrcamento;

import java.time.LocalDate;
import java.util.List;

public record OrcamentoDTO(
        @JsonProperty("_id") Long id,
        @NotNull Cliente cliente,
        @NotNull LocalDate data,
        @NotNull String contato,
        String descricao,

        @NotNull @Pattern(regexp = "Não Fechado|Fechado") String status,
        @NotNull Double total,

        String formaDePagamento,
        String prazoDeEntrega,


        List<vidmarbusiness.crud.vidmarbusiness.models.ItemOrcamento> itens

        ) {
}
