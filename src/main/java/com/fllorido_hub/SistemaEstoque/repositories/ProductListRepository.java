package com.fllorido_hub.SistemaEstoque.repositories;

import com.fllorido_hub.SistemaEstoque.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {
}
