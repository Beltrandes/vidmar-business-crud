package vidmarbusiness.crud.vidmarbusiness.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class ItemOrcamento {

    @Id
    @JsonProperty("_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String material;

    private String dimensoes;

    @NotNull
    private Double quantidade;

    private Double m2;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double m2Total;

    private String detalhes;

    private Double m2Valor;

    private Double valorItem;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;
}
