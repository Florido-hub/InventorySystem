package com.fllorido_hub.SistemaEstoque.repositories;

import com.fllorido_hub.SistemaEstoque.model.Product;
import com.fllorido_hub.SistemaEstoque.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(nativeQuery = true, value = """
		SELECT tb_product.id, tb_product.name, tb_product.price, tb_product.quantity
		FROM product
		INNER JOIN tb_belonging ON tb_product.id = tb_belonging.product_id
		WHERE tb_belonging.list_id = :listId
		ORDER BY tb_belonging.position
			""")
    List<ProductProjection> searchByList(Long listId);
}
