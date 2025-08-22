package com.fllorido_hub.SistemaEstoque.services.impl;

import com.fllorido_hub.SistemaEstoque.dtos.ProductDTO;
import com.fllorido_hub.SistemaEstoque.dtos.ProductRecordDTO;
import com.fllorido_hub.SistemaEstoque.dtos.QuantityDTO;
import com.fllorido_hub.SistemaEstoque.exceptions.InvalidQuantityException;
import com.fllorido_hub.SistemaEstoque.exceptions.ProductNotFoundException;
import com.fllorido_hub.SistemaEstoque.model.Category;
import com.fllorido_hub.SistemaEstoque.model.Product;
import com.fllorido_hub.SistemaEstoque.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Transactional
    public ProductDTO createProduct(ProductRecordDTO productRecordDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productRecordDTO, product);
        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts(){
        var product = productRepository.findAll();
        return product.stream().map(x -> new ProductDTO(x)).toList();
    }

    @Transactional
    public ProductDTO addQuantity(Long id, QuantityDTO quantity) throws ProductNotFoundException {
        var product0 = productRepository.findById(id);
        if(product0.isEmpty())
            throw new ProductNotFoundException("ID not found");
        var product = product0.get();
        product.setQuantity(product.getQuantity() + quantity.getQuantity());

        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO removeQuantity(Long id, QuantityDTO quantity) throws ProductNotFoundException, InvalidQuantityException {
        var productm = productRepository.findById(id);
        if(productm.isEmpty())
            throw new ProductNotFoundException("ID not found");
        var product = productm.get();
        int newQuantity = product.getQuantity() - quantity.getQuantity();
        if (newQuantity < 0) {
            throw new InvalidQuantityException("Nao tem produto suficiente no estoque");
        }
        product.setQuantity(newQuantity);
        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public List<ProductDTO> findByCategory(Category category){
        var list = productRepository.findByCategory(category);
        return list.stream().map(x -> new ProductDTO(x)).toList();
    }
}
