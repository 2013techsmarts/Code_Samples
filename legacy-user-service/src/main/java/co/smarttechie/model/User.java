package co.smarttechie.model;

public record User(
        String username,
        String password,
        String email,
        int age
) {}
