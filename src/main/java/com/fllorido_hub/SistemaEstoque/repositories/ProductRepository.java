package com.fllorido_hub.SistemaEstoque.repositories;

import com.fllorido_hub.SistemaEstoque.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
