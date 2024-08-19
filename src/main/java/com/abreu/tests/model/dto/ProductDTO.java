package com.abreu.tests.model.dto;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Long stock
) {
}
