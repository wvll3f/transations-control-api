package com.wdev.secutity.repositories;

import com.wdev.secutity.entities.Transacao;
import com.wdev.secutity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

}
