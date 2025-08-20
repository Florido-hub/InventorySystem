package com.fllorido_hub.SistemaEstoque.dtos;

import com.fllorido_hub.SistemaEstoque.model.ProductList;

import java.util.Objects;

public class ProductListDTO {
    private Long id;
    private String name;

    public ProductListDTO() {
    }

    public ProductListDTO(ProductList entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductListDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
