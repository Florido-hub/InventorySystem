package com.fllorido_hub.SistemaEstoque.services;
import com.fllorido_hub.SistemaEstoque.enums.Category;
import com.fllorido_hub.SistemaEstoque.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService extends CrudService<Product, Long> {
    List<Product> findAllByCategory(Category category);
}
