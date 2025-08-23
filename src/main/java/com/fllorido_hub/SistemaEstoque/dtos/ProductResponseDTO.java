package com.fllorido_hub.SistemaEstoque.dtos;

import com.fllorido_hub.SistemaEstoque.model.Product;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class ProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Product entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductResponseDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
