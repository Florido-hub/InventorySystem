package com.fllorido_hub.SistemaEstoque.services.impl;

import com.fllorido_hub.SistemaEstoque.model.Cliente;
import com.fllorido_hub.SistemaEstoque.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }
}
