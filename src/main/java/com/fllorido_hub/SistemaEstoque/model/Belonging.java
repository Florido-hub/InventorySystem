package com.fllorido_hub.SistemaEstoque.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_belonging")
public class Belonging {

    @EmbeddedId
    private BelongingPK id = new BelongingPK();

    private Integer position;

    public Belonging() {
    }

    public Belonging(Product product, ProductList productList, Integer position) {
        this.id.setProduct(product);
        this.id.setProductList(productList);
        this.position = position;
    }

    public BelongingPK getId() {
        return id;
    }

    public void setId(BelongingPK id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Belonging belongin)) return false;
        return Objects.equals(id, belongin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
