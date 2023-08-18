package vidmarbusiness.crud.vidmarbusiness.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vidmarbusiness.crud.vidmarbusiness.dtos.WorkDTO;
import vidmarbusiness.crud.vidmarbusiness.services.WorkService;

import java.util.List;

@RestController("/api/obras")
class WorkController {
    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public @ResponseBody List<WorkDTO> list() {
        return workService.list();
    }

    @GetMapping("/{id}")
    public  @ResponseBody WorkDTO findById(@PathVariable @NotNull @Positive Long id) {
        return workService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public WorkDTO create(@RequestBody @Valid WorkDTO work) {
        return workService.create(work);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        workService.delete(id);
    }
}
