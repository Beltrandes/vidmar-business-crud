package vidmarbusiness.crud.vidmarbusiness.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.exceptions.RecordNotFoundException;
import vidmarbusiness.crud.vidmarbusiness.mappers.WorkMapper;
import vidmarbusiness.crud.vidmarbusiness.repositorys.WorkRepository;

import java.util.List;

@Validated
@Service
public class WorkService {
    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    public WorkService(WorkRepository workRepository, WorkMapper workMapper) {
        this.workRepository = workRepository;
        this.workMapper = workMapper;
    }

    public List<WorkDTO> list() {
        return workRepository.findAll().stream().map(workMapper::toDTO).toList();
    }

    public WorkDTO findById(@NotNull @PathVariable @Positive Long id) {
        return workRepository.findById(id).map(workMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public WorkDTO create(@Valid @NotNull WorkDTO work) {
        return workMapper.toDTO(workRepository.save(workMapper.toEntity(work)));
    }

    public WorkDTO update(@PathVariable @NotNull @Positive Long id, @Valid @NotNull WorkDTO work) {
        return workRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(work.name());
                    recordFound.setCliente(work.cliente());
                    recordFound.setAddress(work.address());
                    recordFound.setInitialDate(work.initialDate());
                    recordFound.setFinishDate(work.finishDate());
                    return workMapper.toDTO(workRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        workRepository.delete(workRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
