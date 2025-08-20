package com.fllorido_hub.SistemaEstoque.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_productlist")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public ProductList() {
    }

    public ProductList(Long id, String category) {
        this.id = id;
        this.name = category;
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

    public void setName(String category) {
        this.name = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductList that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
