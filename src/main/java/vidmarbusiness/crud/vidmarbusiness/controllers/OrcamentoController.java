package vidmarbusiness.crud.vidmarbusiness.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vidmarbusiness.crud.vidmarbusiness.dtos.OrcamentoDTO;
import vidmarbusiness.crud.vidmarbusiness.services.OrcamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {
    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService) { this.orcamentoService = orcamentoService; }

    @GetMapping
    public @ResponseBody List<OrcamentoDTO> list() {
        return orcamentoService.list();
    }

    @GetMapping("/{id}")
    public OrcamentoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return orcamentoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrcamentoDTO create(@RequestBody @Valid OrcamentoDTO orcamento) {
        return orcamentoService.create(orcamento);
    }

    @PutMapping("/{id}")
    public OrcamentoDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid OrcamentoDTO orcamento) {
        return orcamentoService.update(id, orcamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) { orcamentoService.delete(id); }

}
