package com.fllorido_hub.SistemaEstoque.repositories;

import com.fllorido_hub.SistemaEstoque.model.Belongin;
import com.fllorido_hub.SistemaEstoque.model.BelonginPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BelonginRepository extends JpaRepository<Belongin, BelonginPK> {
}
