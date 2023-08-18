package vidmarbusiness.crud.vidmarbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.exceptions.RecordNotFoundException;
import vidmarbusiness.crud.vidmarbusiness.mappers.ClienteMapper;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ClienteRepository;

@Validated
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> list() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDTO).toList();
    }

    public ClienteDTO findById(@PathVariable @NotNull @Positive Long id) {
        return clienteRepository.findById(id).map(clienteMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ClienteDTO create(@Valid @NotNull ClienteDTO cliente) {
        return clienteMapper.toDTO(clienteRepository.save(clienteMapper.toEntity(cliente)));
    }

    public ClienteDTO update(@PathVariable @NotNull @Positive Long id, @Valid @NotNull ClienteDTO cliente) {
        return clienteRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(cliente.name());
                recordFound.setAddress(cliente.address());
                recordFound.setWorks(cliente.works());
                recordFound.setNumber(cliente.number());
                return clienteMapper.toDTO(clienteRepository.save(recordFound));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        clienteRepository.delete(clienteRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
