package vidmarbusiness.crud.vidmarbusiness.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.OrcamentoDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.exceptions.RecordNotFoundException;
import vidmarbusiness.crud.vidmarbusiness.mappers.OrcamentoMapper;
import vidmarbusiness.crud.vidmarbusiness.repositorys.OrcamentoRepository;

import java.util.List;

@Service
@Validated
public class OrcamentoService {
    private final OrcamentoRepository orcamentoRepository;

    private final OrcamentoMapper orcamentoMapper;

    public OrcamentoService(OrcamentoRepository orcamentoRepository, OrcamentoMapper orcamentoMapper) {
        this.orcamentoMapper = orcamentoMapper;
        this.orcamentoRepository = orcamentoRepository;

    }

    public List<OrcamentoDTO> list() {
        return orcamentoRepository.findAll().stream().map(orcamentoMapper::toDTO).toList();
    }

    public OrcamentoDTO findById(@NotNull @Positive Long id) {
        return orcamentoRepository.findById(id).map(orcamentoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public OrcamentoDTO create(@Valid @NotNull OrcamentoDTO orcamento) {
        return orcamentoMapper.toDTO(orcamentoRepository.save(orcamentoMapper.toEntity(orcamento)));
    }

    public OrcamentoDTO update(@NotNull @Positive Long id, @Valid @NotNull OrcamentoDTO orcamento) {
        return orcamentoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCliente(orcamento.cliente());
                    recordFound.setData(orcamento.data());
                    recordFound.setContato(orcamento.contato());
                    recordFound.setDescricao(orcamento.descricao());
                    recordFound.setTotal(orcamento.total());
                    recordFound.setStatus(orcamento.status());
                    recordFound.setItens(orcamento.itens());
                    return orcamentoMapper.toDTO(orcamentoRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        orcamentoRepository.delete(orcamentoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
