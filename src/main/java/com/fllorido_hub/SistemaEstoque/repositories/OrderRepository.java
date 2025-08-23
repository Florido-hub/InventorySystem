package com.fllorido_hub.SistemaEstoque.repositories;
import com.fllorido_hub.SistemaEstoque.enums.Status;
import com.fllorido_hub.SistemaEstoque.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByStatus(Status status);
}
