package com.fllorido_hub.SistemaEstoque.repositories;
import com.fllorido_hub.SistemaEstoque.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
