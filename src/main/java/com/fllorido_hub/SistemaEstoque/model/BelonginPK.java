package com.fllorido_hub.SistemaEstoque.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BelonginPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ProductList productList;

    public BelonginPK() {
    }

    public BelonginPK(Product product, ProductList productList) {
        this.product = product;
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BelonginPK that)) return false;
        return Objects.equals(product, that.product) && Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productList);
    }
}
