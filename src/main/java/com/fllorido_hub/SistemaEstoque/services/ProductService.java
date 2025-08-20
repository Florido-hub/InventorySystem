package com.fllorido_hub.SistemaEstoque.services;

import com.fllorido_hub.SistemaEstoque.dtos.ProductDTO;
import com.fllorido_hub.SistemaEstoque.model.Product;
import com.fllorido_hub.SistemaEstoque.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<ProductDTO> getAllProducts(){
        var product = productRepository.findAll();
        return product.stream().map(x -> new ProductDTO(x)).toList();
    }

    public Product addQuantity(Long id, int quantity){
        var product = productRepository.findById(id).get();
        product.setQuantity(product.getQuantity() + quantity);
        return productRepository.save(product);
    }

    public Product removeQuantity(Long id, int quantity) {
        var product = productRepository.findById(id).get();
        product.setQuantity(product.getQuantity() - quantity);
        return productRepository.save(product);
    }
}
