package co.smarttechie.service;

import co.smarttechie.entity.Item;
import co.smarttechie.mapper.ItemMapper;
import co.smarttechie.model.ItemDto;
import co.smarttechie.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<ItemDto> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable)
                .map(ItemMapper::toDto);
    }

    public Optional<ItemDto> getItemById(Long id) {
        return itemRepository.findById(id)
                .map(ItemMapper::toDto);
    }

    @Transactional
    public ItemDto createItem(ItemDto itemDto) {
        Item savedItem = itemRepository.save(ItemMapper.toEntity(itemDto));
        return ItemMapper.toDto(savedItem);
    }

    @Transactional
    public Optional<ItemDto> updateItem(Long id, ItemDto itemDto) {
        return itemRepository.findById(id)
                .map(existing -> {
                    existing.setDescription(itemDto.description());
                    existing.setName(itemDto.name());
                    return ItemMapper.toDto(itemRepository.save(existing));
                });
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

}