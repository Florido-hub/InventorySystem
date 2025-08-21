package com.fllorido_hub.SistemaEstoque.controller;

import com.fllorido_hub.SistemaEstoque.dtos.ProductDTO;
import com.fllorido_hub.SistemaEstoque.dtos.ProductRecordDTO;
import com.fllorido_hub.SistemaEstoque.dtos.QuantityDTO;
import com.fllorido_hub.SistemaEstoque.model.Category;
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
    public ResponseEntity<Product> createProduct(@RequestBody ProductRecordDTO product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PatchMapping("/{id}/add")
    public ResponseEntity<Product> addQuantity(@PathVariable Long id,@RequestBody QuantityDTO quantity){
        return ResponseEntity.status(HttpStatus.OK).body(productService.addQuantity(id,quantity));
    }

    @PatchMapping("/{id}/remove")
    public ResponseEntity<Product> removeQuantity(@PathVariable Long id, @RequestBody QuantityDTO quantity) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.removeQuantity(id,quantity));
    }

    @GetMapping("/categoria/{category}")
    public ResponseEntity<List<ProductDTO>> findByCategoria(@PathVariable Category category){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByCategory(category));
    }
}
