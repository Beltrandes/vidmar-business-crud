package vidmarbusiness.crud.vidmarbusiness.mappers;

import org.springframework.stereotype.Component;

import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.models.Cliente;

@Component
public class ClienteMapper {
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return new ClienteDTO(cliente.getId(), cliente.getName(), cliente.getAddress(), cliente.getWorks(), cliente.getNumber());
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
        cliente.setWorks(clienteDTO.works());
        cliente.setNumber(clienteDTO.number());

        return cliente;
    }
}
