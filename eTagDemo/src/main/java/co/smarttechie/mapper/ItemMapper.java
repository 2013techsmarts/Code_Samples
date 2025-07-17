package co.smarttechie.mapper;

import co.smarttechie.entity.Item;
import co.smarttechie.model.ItemDto;

public class ItemMapper {
    public static ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getVersion()
        );
    }

    public static Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setName(dto.name());
        item.setDescription(dto.description());
        return item;
    }
}
