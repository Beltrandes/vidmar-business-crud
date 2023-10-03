package vidmarbusiness.crud.vidmarbusiness.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orcamentos")
public class Orcamento {

        @Id
        @JsonProperty("_id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;


        @ManyToOne
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;

        @Column(name = "forma_de_pagto")
        private String formaDePagamento;

        @Column(name = "prazo_de_entrega")
        private String prazoDeEntrega;

        @Column(name = "data", length = 80)
        @NotNull
        private LocalDate data;

        @NotNull
        @NotBlank
        @Column(name = "contato")
        private String contato;


        @Column(name = "descricao")
        private String descricao;

        @Column(name = "status", length = 20)
        @Pattern(regexp = "Não Fechado|Fechado")
        private String status = "Não Fechado";

        @Column(name = "valorTotal")
        private double total;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "orcamento")
        @JsonIgnore
        private List<ItemOrcamento> itens;

        public double calcTotal() {
                if (itens != null && !itens.isEmpty()) {
                        double total = itens.stream().mapToDouble(ItemOrcamento::getValorTotal).sum();
                        this.total = total;
                } else {
                        this.total = 0.0;
                }
                return this.total;
        }

    }



