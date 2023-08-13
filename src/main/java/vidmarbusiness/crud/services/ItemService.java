package vidmarbusiness.crud.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import vidmarbusiness.crud.dtos.ItemDTO;
import vidmarbusiness.crud.exceptions.RecordNotFoundException;
import vidmarbusiness.crud.mappers.ItemMapper;
import vidmarbusiness.crud.repositorys.ItemRepository;

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
