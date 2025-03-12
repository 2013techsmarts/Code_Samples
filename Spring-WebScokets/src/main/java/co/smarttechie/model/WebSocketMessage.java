package co.smarttechie.model;

public record WebSocketMessage(
    String type,
    Object payload
) {} 