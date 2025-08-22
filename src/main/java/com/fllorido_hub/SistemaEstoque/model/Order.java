package com.fllorido_hub.SistemaEstoque.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private Set<OrderItem> orderitem = new HashSet<>();

    private Double totalValue;
    private LocalDate date;


    public Order() {
    }

    public Order(Long id, Cliente customer, Set<OrderItem> orderitem, Double totalValue, LocalDate date) {
        this.id = id;
        this.cliente = customer;
        this.orderitem = orderitem;
        this.totalValue = totalValue;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCustomer(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<OrderItem> getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(Set<OrderItem> orderitem) {
        this.orderitem = orderitem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
