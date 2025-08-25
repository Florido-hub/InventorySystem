package com.fllorido_hub.SistemaEstoque.repositories;

import com.fllorido_hub.SistemaEstoque.enums.Category;
import com.fllorido_hub.SistemaEstoque.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategory(Category category);

	List<Product> findByPriceBetween(Double min, Double max);
}
