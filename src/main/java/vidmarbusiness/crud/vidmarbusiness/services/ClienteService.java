package vidmarbusiness.crud.vidmarbusiness.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.exceptions.RecordNotFoundException;
import vidmarbusiness.crud.vidmarbusiness.mappers.ClienteMapper;
import vidmarbusiness.crud.vidmarbusiness.mappers.WorkMapper;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;
import vidmarbusiness.crud.vidmarbusiness.models.Work;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ClienteRepository;

@Validated
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    private final WorkMapper workMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper, WorkMapper workMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.workMapper = workMapper;

    }

    public List<ClienteDTO> list() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDTO).toList();
    }

    public ClienteDTO findById(@NotNull @Positive Long id) {
        return clienteRepository.findById(id).map(clienteMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ClienteDTO create(@Valid @NotNull ClienteDTO cliente) {
        return clienteMapper.toDTO(clienteRepository.save(clienteMapper.toEntity(cliente)));
    }

    public ClienteDTO update(@NotNull @Positive Long id, @Valid @NotNull ClienteDTO cliente) {
        return clienteRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(cliente.name());
                recordFound.setAddress(cliente.address());
                recordFound.setNumber(cliente.number());
                return clienteMapper.toDTO(clienteRepository.save(recordFound));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ClienteDTO updateWorks(Long clienteId, List<WorkDTO> obras) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RecordNotFoundException(clienteId));

        List<Work> works = obras.stream()
                .map(obraDTO -> {
                    Work obra = workMapper.toEntity(obraDTO);
                    obra.setCliente(cliente);
                    return obra;
                })
                .toList();
        cliente.setWorks(works);
        clienteRepository.save(cliente);

        return clienteMapper.toDTO(cliente);
    }

    public void delete(@NotNull @Positive Long id) {
        clienteRepository.delete(clienteRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
