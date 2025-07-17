package co.smarttechie.model;

public record ItemDto(
        Long id,
        String name,
        String description,
        Long version
) {
    public ItemDto(String name, String description) {
        this(null, name, description, null);
    }
}
