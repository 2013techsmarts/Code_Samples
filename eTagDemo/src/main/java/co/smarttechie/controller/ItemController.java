package co.smarttechie.controller;

import co.smarttechie.model.ItemDto;
import co.smarttechie.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(item -> {
                    String eTag = generateETag(item);
                    return ResponseEntity.ok()
                            .eTag(eTag)
                            .body(item);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(
            @PathVariable Long id,
            @RequestBody ItemDto itemToUpdate,
            @RequestHeader(value = "If-Match", required = false) String ifMatch) {

            Optional<ItemDto> existing = itemService.getItemById(id);
            if (existing.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            String currentETag = generateETag(existing.get());

            if (ifMatch == null || !ifMatch.equals(currentETag)) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
            }

            return itemService.updateItem(id, itemToUpdate)
                    .map(updatedItem -> ResponseEntity.ok()
                            .eTag(generateETag(updatedItem))
                            .body(updatedItem))
                    .orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        ItemDto createdItem = itemService.createItem(itemDto);
        String eTag = generateETag(createdItem);

        return ResponseEntity
                .created(URI.create("/items/" + createdItem.id()))
                .eTag(eTag)
                .body(createdItem);
    }

    private String generateETag(ItemDto item) {
        return "\"" + item.version() + "\"";
    }
}