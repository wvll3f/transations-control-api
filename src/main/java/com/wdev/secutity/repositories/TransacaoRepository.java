package com.wdev.secutity.repositories;

import com.wdev.secutity.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

    List<Transacao> findTransacaoByDateBetween(LocalDate dataInicial, LocalDate dataFinal);

}
