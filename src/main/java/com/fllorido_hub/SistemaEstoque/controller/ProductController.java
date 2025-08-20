package com.fllorido_hub.SistemaEstoque.controller;

import com.fllorido_hub.SistemaEstoque.dtos.ProductDTO;
import com.fllorido_hub.SistemaEstoque.model.Product;
import com.fllorido_hub.SistemaEstoque.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
}
