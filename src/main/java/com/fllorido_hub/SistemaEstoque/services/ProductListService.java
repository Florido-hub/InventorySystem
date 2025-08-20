package com.fllorido_hub.SistemaEstoque.services;

import com.fllorido_hub.SistemaEstoque.dtos.ProductListDTO;
import com.fllorido_hub.SistemaEstoque.repositories.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductListService {

    @Autowired
    public ProductListRepository productListRepository;

    public List<ProductListDTO> getAllLists(){
        var lists = productListRepository.findAll();
        return lists.stream().map(x -> new ProductListDTO(x)).toList();
    }
}
