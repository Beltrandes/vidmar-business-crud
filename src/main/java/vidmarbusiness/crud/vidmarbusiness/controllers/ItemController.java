package vidmarbusiness.crud.vidmarbusiness.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
