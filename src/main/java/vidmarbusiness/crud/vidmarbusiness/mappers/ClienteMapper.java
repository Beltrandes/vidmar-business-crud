package vidmarbusiness.crud.vidmarbusiness.mappers;

import org.springframework.stereotype.Component;

import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;

import java.util.List;

@Component
public class ClienteMapper {
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        List<WorkDTO> works = cliente.getWorks()
                .stream()
                .map(work -> new WorkDTO(work.getId(), work.getName(), work.getCliente(), work.getAddress(), work.getServiceType(), work.getInitialDate(), work.getFinishDate()))
                .toList();

        return new ClienteDTO(cliente.getId(), cliente.getName(), cliente.getAddress(), cliente.getNumber(), cliente.getArrowDirection(), works);
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        if (clienteDTO.id() != null) {
            cliente.setId(clienteDTO.id());
        }
        cliente.setName(clienteDTO.name());
        cliente.setAddress(clienteDTO.address());
        cliente.setNumber(clienteDTO.number());
        cliente.setArrowDirection(clienteDTO.arrowDirection());

        return cliente;
    }
}
