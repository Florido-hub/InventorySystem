package com.fllorido_hub.SistemaEstoque.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductRequestDTO(
        @NotBlank @Size(min = 3, max = 25, message = "Nome invalido")
        String name,
        @NotNull @PositiveOrZero (message = "preco invalido")
        Double price,
        @PositiveOrZero (message = "quantidade invalida")
        Integer quantity){
}
