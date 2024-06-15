package com.wdev.secutity.repositories;

import com.wdev.secutity.entities.MetodosPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodosPagamentoRepository extends JpaRepository<MetodosPagamento, Long> {

    MetodosPagamento findByName(String name);
}
