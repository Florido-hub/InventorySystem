package com.fllorido_hub.SistemaEstoque.controller;

import com.fllorido_hub.SistemaEstoque.dtos.ProductListDTO;
import com.fllorido_hub.SistemaEstoque.services.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListController {

    @Autowired
    private ProductListService productListService;

    @GetMapping
    public ResponseEntity<List<ProductListDTO>> getAllLists(){
        return ResponseEntity.status(HttpStatus.OK).body(productListService.getAllLists());
    }
}
