package vidmarbusiness.crud.vidmarbusiness.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.ItemDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.exceptions.RecordNotFoundException;
import vidmarbusiness.crud.vidmarbusiness.mappers.ClienteMapper;
import vidmarbusiness.crud.vidmarbusiness.mappers.WorkMapper;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;
import vidmarbusiness.crud.vidmarbusiness.models.Work;
import vidmarbusiness.crud.vidmarbusiness.repositorys.WorkRepository;

import java.util.List;

@Validated
@Service
public class WorkService {
    private final WorkRepository workRepository;

    private final WorkMapper workMapper;

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    public WorkService(WorkRepository workRepository, WorkMapper workMapper, ClienteService clienteService, ClienteMapper clienteMapper) {
        this.workRepository = workRepository;
        this.workMapper = workMapper;
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    public List<WorkDTO> list() {
        return workRepository.findAll().stream().map(workMapper::toDTO).toList();
    }

    public WorkDTO findById(@NotNull @Positive Long id) {
        return workRepository.findById(id).map(workMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public WorkDTO create(@Valid @NotNull WorkDTO work) {
        ClienteDTO clienteDTO = clienteService.findById(work.cliente().getId());
        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        Work novaObra = new Work();

        novaObra.setName(work.name());
        novaObra.setAddress(work.address());
        novaObra.setCliente(cliente);
        novaObra.setServiceType(work.serviceType());
        novaObra.setInitialDate(work.initialDate());
        novaObra.setFinishDate(work.finishDate());



        Work obraSalva = workRepository.save(novaObra);

        return workMapper.toDTO(obraSalva);
    }

    public WorkDTO update(@NotNull @Positive Long id, @Valid @NotNull WorkDTO work) {
        return workRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(work.name());
                    recordFound.setAddress(work.address());
                    recordFound.setCliente(work.cliente());
                    recordFound.setServiceType(work.serviceType());
                    recordFound.setInitialDate(work.initialDate());
                    recordFound.setFinishDate(work.finishDate());
                    return workMapper.toDTO(workRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }


    public void delete(@NotNull @Positive Long id) {
        workRepository.delete(workRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
