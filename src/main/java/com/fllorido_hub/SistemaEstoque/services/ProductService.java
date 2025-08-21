package com.fllorido_hub.SistemaEstoque.services;

import com.fllorido_hub.SistemaEstoque.dtos.ProductDTO;
import com.fllorido_hub.SistemaEstoque.dtos.ProductListDTO;
import com.fllorido_hub.SistemaEstoque.dtos.QuantityDTO;
import com.fllorido_hub.SistemaEstoque.model.Product;
import com.fllorido_hub.SistemaEstoque.projections.ProductProjection;
import com.fllorido_hub.SistemaEstoque.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product createProduct(Product product){
        productRepository.save(product);
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts(){
        var product = productRepository.findAll();
        return product.stream().map(x -> new ProductDTO(x)).toList();
    }

    @Transactional
    public Product addQuantity(Long id, QuantityDTO quantity){
        var product = productRepository.findById(id).get();
        product.setQuantity(product.getQuantity() + quantity.getQuantity());
        return productRepository.save(product);
    }

    @Transactional
    public Product removeQuantity(Long id, QuantityDTO quantity) {
        var productm = productRepository.findById(id).get();
        productm.setQuantity(productm.getQuantity() - quantity.getQuantity());
        if (productm.getQuantity() <= 0) {
            productRepository.delete(productm);
        } else {
            productRepository.save(productm);
        }
        return productm;
    }

    @Transactional
    public List<ProductListDTO> searchByList(Long listId){
        List<ProductProjection> productProjection = productRepository.searchByList(listId);
        return productProjection.stream().map(x -> new ProductListDTO(x)).toList();
    }
}
