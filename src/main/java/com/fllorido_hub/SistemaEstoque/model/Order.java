package com.fllorido_hub.SistemaEstoque.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fllorido_hub.SistemaEstoque.enums.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status;


    public Order() {
    }

    public Order(Long id, Cliente cliente, Double totalValue, LocalDate date, Status status) {
        this.id = id;
        this.cliente = cliente;
        this.totalValue = totalValue;
        this.date = date;
        this.status = status;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<OrderItem> getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(Set<OrderItem> orderitem) {
        this.orderitem = orderitem;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
