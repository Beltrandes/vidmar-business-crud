package vidmarbusiness.crud.vidmarbusiness.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vidmarbusiness.crud.vidmarbusiness.dtos.ClienteDTO;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.services.WorkService;

import java.util.List;
@RestController
@RequestMapping("/api/obras")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) { this.workService = workService; }

    @GetMapping
    public @ResponseBody List<WorkDTO> list() {
        return workService.list();
    }

    @GetMapping("/{id}")
    public WorkDTO findById(@PathVariable @NotNull @Positive Long id) {
        return workService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public WorkDTO create(@RequestBody @Valid WorkDTO work) {
        return workService.create(work);
    }

    @PutMapping("/{id}")
    public WorkDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid WorkDTO work) {
        return workService.update(id, work);
    }
}
