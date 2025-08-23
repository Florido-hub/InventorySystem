package com.fllorido_hub.SistemaEstoque.dtos;

import com.fllorido_hub.SistemaEstoque.model.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderRequestDTO(
        @NotNull Long clienteId,
        @NotEmpty Set<OrderItem> itens
) { }
