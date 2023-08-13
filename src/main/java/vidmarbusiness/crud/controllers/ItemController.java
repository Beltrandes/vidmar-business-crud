package vidmarbusiness.crud.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vidmarbusiness.crud.dtos.ItemDTO;
import vidmarbusiness.crud.services.ItemService;

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
}
