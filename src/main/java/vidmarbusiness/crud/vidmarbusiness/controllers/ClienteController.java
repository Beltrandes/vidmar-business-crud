package vidmarbusiness.crud.vidmarbusiness.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public @ResponseBody List<ClienteDTO> list() {
        return clienteService.list();
    }

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable @NotNull @Positive Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClienteDTO create(@RequestBody @Valid ClienteDTO cliente) {
        return clienteService.create(cliente);
    }

    @PutMapping("/{id}")
    public ClienteDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid ClienteDTO cliente) {
        return clienteService.update(id, cliente);
    }

    @PutMapping("/obras/{id}")
    public ClienteDTO updateWorks(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid List<WorkDTO> obras) {
        return clienteService.updateWorks(id, obras);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        clienteService.delete(id);
    }
}
