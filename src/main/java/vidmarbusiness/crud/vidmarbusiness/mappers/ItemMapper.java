package vidmarbusiness.crud.vidmarbusiness.mappers;

import org.springframework.stereotype.Component;

import vidmarbusiness.crud.vidmarbusiness.dtos.ItemDTO;
import vidmarbusiness.crud.vidmarbusiness.models.Item;

@Component
public class ItemMapper {
    public ItemDTO toDTO(Item item) {
        if (item == null) {
            return null;
        }

        return new ItemDTO(item.getId(), item.getAmount(), item.getName(), item.getType(), item.getStatus());
    }

    public Item toEntity(ItemDTO itemDTO) {
        if (itemDTO == null) {
            return null;
        }

        Item item = new Item();
        if (itemDTO.id() != null) {
            item.setId(itemDTO.id());
        }
        item.setAmount(itemDTO.amount());
        item.setName(itemDTO.name());
        item.setType(itemDTO.type());
        item.setStatus(itemDTO.status());

        return item;
    }
}
