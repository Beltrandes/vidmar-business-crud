package vidmarbusiness.crud.vidmarbusiness.mappers;

import org.springframework.stereotype.Component;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.models.Work;

@Component
public class WorkMapper {
    public WorkDTO toDTO(Work work) {
        if (work == null) {
            return null;
        }

        return new WorkDTO(work.getId(), work.getName(), work.getCliente(), work.getAddress(), work.getInitialDate(), work.getFinishDate());
    }

    public Work toEntity(WorkDTO workDTO) {
        if (workDTO == null) {
            return null;
        }

        Work work = new Work();
        if (workDTO.id() != null) {
            work.setId(workDTO.id());
        }
        work.setName(workDTO.name());
        work.setCliente(workDTO.cliente());
        work.setAddress(workDTO.address());
        work.setInitialDate(workDTO.initialDate());
        work.setFinishDate(workDTO.finishDate());
        return work;
    }
}
