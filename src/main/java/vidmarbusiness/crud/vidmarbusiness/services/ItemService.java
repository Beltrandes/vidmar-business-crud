package vidmarbusiness.crud.vidmarbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import vidmarbusiness.crud.vidmarbusiness.repositorys.ItemRepository;
import vidmarbusiness.crud.vidmarbusiness.dtos.ItemDTO;
import vidmarbusiness.crud.vidmarbusiness.exceptions.RecordNotFoundException;
import vidmarbusiness.crud.vidmarbusiness.mappers.ItemMapper;

@Validated
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDTO> list() {
        return itemRepository.findAll().stream().map(itemMapper::toDTO).toList();
            
    }

    public ItemDTO findById(@PathVariable @NotNull @Positive Long id) {
        return itemRepository.findById(id).map(itemMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }
}
