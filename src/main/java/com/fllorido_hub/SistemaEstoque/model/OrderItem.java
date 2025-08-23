package com.fllorido_hub.SistemaEstoque.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_orderitem")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK produtoId = new OrderItemPK();

    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Product product, Order order, Integer quantity) {
        this.produtoId.setProduct(product);
        this.produtoId.setOrder(order);
        this.quantity = quantity;
    }

    public OrderItemPK getId() {
        return produtoId;
    }

    public void setId(OrderItemPK id) {
        this.produtoId = id;
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
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(produtoId, orderItem.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId);
    }
}
