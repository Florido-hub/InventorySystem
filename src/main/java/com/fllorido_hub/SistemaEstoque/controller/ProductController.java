package com.fllorido_hub.SistemaEstoque.controller;

import com.fllorido_hub.SistemaEstoque.dtos.ProductResponseDTO;
import com.fllorido_hub.SistemaEstoque.dtos.ProductRequestDTO;
import com.fllorido_hub.SistemaEstoque.enums.Category;
import com.fllorido_hub.SistemaEstoque.exceptions.InvalidQuantityException;
import com.fllorido_hub.SistemaEstoque.exceptions.ProductNotFoundException;
import com.fllorido_hub.SistemaEstoque.services.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PatchMapping("/{id}/add")
    public ResponseEntity<ProductResponseDTO> addQuantity(@PathVariable Long id, @RequestParam int quantity) throws ProductNotFoundException {
        var product = productService.addQuantity(id, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PatchMapping("/{id}/remove")
    public ResponseEntity<ProductResponseDTO> removeQuantity(@PathVariable Long id, @RequestParam int quantity) throws InvalidQuantityException, ProductNotFoundException {
        var product = productService.removeQuantity(id, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/categoria/{category}")
    public ResponseEntity<List<ProductResponseDTO>> findByCategoria(@PathVariable Category category){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByCategory(category));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponseDTO>> filterByPrice(@RequestParam Double minprice, @RequestParam Double maxprice){
        var n = productService.filterByPrice(minprice, maxprice);
        return ResponseEntity.status(HttpStatus.OK).body(n);
    }
}
