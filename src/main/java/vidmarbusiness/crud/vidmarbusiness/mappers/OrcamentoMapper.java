package vidmarbusiness.crud.vidmarbusiness.mappers;

import org.springframework.stereotype.Component;
import vidmarbusiness.crud.vidmarbusiness.dtos.OrcamentoDTO;
import vidmarbusiness.crud.vidmarbusiness.models.Orcamento;

@Component
public class OrcamentoMapper {
    public OrcamentoDTO toDTO(Orcamento orcamento) {
        if (orcamento == null) {
            return null;
        }
        return new OrcamentoDTO(orcamento.getId(), orcamento.getCliente(), orcamento.getData(), orcamento.getContato(), orcamento.getDescricao(), orcamento.getStatus(), orcamento.getTotal(), orcamento.getFormaDePagamento(), orcamento.getPrazoDeEntrega(), orcamento.getItens());
    }

    public Orcamento toEntity(OrcamentoDTO orcamentoDTO) {
        if (orcamentoDTO == null) {
            return null;
        }

        Orcamento orcamento = new Orcamento();

        if (orcamentoDTO.id() != null) {
            orcamento.setId(orcamentoDTO.id());
        }
        orcamento.setCliente(orcamentoDTO.cliente());
        orcamento.setData(orcamentoDTO.data());
        orcamento.setContato(orcamentoDTO.contato());
        orcamento.setDescricao(orcamentoDTO.descricao());
        orcamento.setStatus(orcamentoDTO.status());
        orcamento.setTotal(orcamentoDTO.total());
        orcamento.setItens(orcamentoDTO.itens());
        orcamento.setFormaDePagamento(orcamentoDTO.formaDePagamento());
        orcamento.setPrazoDeEntrega(orcamentoDTO.prazoDeEntrega());

        return orcamento;
    }
}
