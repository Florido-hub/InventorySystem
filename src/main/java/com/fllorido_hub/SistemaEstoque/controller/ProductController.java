package com.fllorido_hub.SistemaEstoque.controller;

import com.fllorido_hub.SistemaEstoque.dtos.ProductDTO;
import com.fllorido_hub.SistemaEstoque.dtos.ProductRecordDTO;
import com.fllorido_hub.SistemaEstoque.dtos.QuantityDTO;
import com.fllorido_hub.SistemaEstoque.exceptions.InvalidQuantityException;
import com.fllorido_hub.SistemaEstoque.exceptions.ProductNotFoundException;
import com.fllorido_hub.SistemaEstoque.model.Category;
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
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductRecordDTO product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PatchMapping("/{id}/add")
    public ResponseEntity<ProductDTO> addQuantity(@PathVariable Long id,@RequestBody @Valid QuantityDTO quantity) throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addQuantity(id,quantity));
    }

    @PatchMapping("/{id}/remove")
    public ResponseEntity<ProductDTO> removeQuantity(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantity) throws InvalidQuantityException, ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.removeQuantity(id,quantity));
    }

    @GetMapping("/categoria/{category}")
    public ResponseEntity<List<ProductDTO>> findByCategoria(@PathVariable Category category){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByCategory(category));
    }
}
