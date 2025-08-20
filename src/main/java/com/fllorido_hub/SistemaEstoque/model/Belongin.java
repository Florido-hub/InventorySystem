package com.fllorido_hub.SistemaEstoque.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_belongin")
public class Belongin {

    @EmbeddedId
    private BelonginPK id = new BelonginPK();

    private Integer position;

    public Belongin() {
    }

    public Belongin(Product product, ProductList productList, Integer position) {
        this.id.setProduct(product);
        this.id.setProductList(productList);
        this.position = position;
    }

    public BelonginPK getId() {
        return id;
    }

    public void setId(BelonginPK id) {
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
        if (!(o instanceof Belongin belongin)) return false;
        return Objects.equals(id, belongin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
