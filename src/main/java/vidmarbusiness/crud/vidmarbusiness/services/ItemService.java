package vidmarbusiness.crud.vidmarbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
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

    public ItemDTO create(@Valid @NotNull ItemDTO item) {
        return itemMapper.toDTO(itemRepository.save(itemMapper.toEntity(item)));
    }

    public ItemDTO updateAmount(@NotNull @Positive Long id, @RequestBody Integer newAmount) {
        return itemRepository.findById(id)
            .map(recordFound -> {
                recordFound.setAmount(newAmount);
                return itemMapper.toDTO(itemRepository.save(recordFound));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    

    public ItemDTO update(@NotNull @Positive Long id, @Valid @NotNull ItemDTO item) {
        return itemRepository.findById(id)
            .map(recordFound -> {
                recordFound.setAmount(item.amount());
                recordFound.setName(item.name());
                recordFound.setType(item.type());
                return itemMapper.toDTO(itemRepository.save(recordFound));  
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        itemRepository.delete(itemRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
    
}
