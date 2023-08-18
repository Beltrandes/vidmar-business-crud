package vidmarbusiness.crud.vidmarbusiness.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import vidmarbusiness.crud.vidmarbusiness.dtos.ItemDTO;
import vidmarbusiness.crud.vidmarbusiness.services.ItemService;

@RestController
@RequestMapping("/api/itens-estoque")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public @ResponseBody List<ItemDTO> list() {
        return itemService.list();
    }

    @GetMapping("/{id}")
    public ItemDTO findById(@PathVariable @NotNull @Positive Long id) {
        return itemService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ItemDTO create(@RequestBody @Valid ItemDTO item) {
        return itemService.create(item);
    }

    @PutMapping("/{id}")
    public ItemDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull ItemDTO item) {
        return itemService.update(id, item);
    }

    @PatchMapping("/{id}/amount")
    public ItemDTO updateAmount(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull Integer newAmount) {
        return itemService.updateAmount(id, newAmount);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        itemService.delete(id);
    }


    
}
