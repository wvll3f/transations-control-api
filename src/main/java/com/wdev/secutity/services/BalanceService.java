package com.wdev.secutity.services;

import com.wdev.secutity.dtos.BalanceDTO;
import com.wdev.secutity.entities.Transacao;
import com.wdev.secutity.repositories.TransacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BalanceService {

    private final TransacaoRepository transacaoRepository;


    public BalanceService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public ResponseEntity<String> generateBalance(JwtAuthenticationToken token,
                                                  LocalDate dataInicial,
                                                  LocalDate dataFinal){


        var valoresEntrada = transacaoRepository.findTransacaoByDateBetween(dataInicial,dataFinal )
                .stream()
                .filter(transacao -> transacao.getUser().getId().equals(UUID.fromString(token.getName())))
                .map(transacao -> transacao.toBalanceDTO(transacao))
                .filter(balanceDTO -> balanceDTO.getType().getCodigo().equals("E"))
                .map(balanceDTO -> balanceDTO.getBalance())
                .collect(Collectors.summingDouble(BigDecimal::doubleValue));

        var valoresSaida = transacaoRepository.findTransacaoByDateBetween(dataInicial,dataFinal)
                .stream()
                .filter(transacao -> transacao.getUser().getId().equals(UUID.fromString(token.getName())))
                .map(transacao -> transacao.toBalanceDTO(transacao))
                .filter(balanceDTO -> balanceDTO.getType().getCodigo().equals("S"))
                .map(balanceDTO -> balanceDTO.getBalance())
                .collect(Collectors.summingDouble(BigDecimal::doubleValue));

        double total = valoresEntrada - valoresSaida;

        DecimalFormat formato = new DecimalFormat("#.##");
        var totalFormatado = (formato.format(total).replace(",", "."));

        return ResponseEntity.ok(totalFormatado);

    }

    public ResponseEntity<String> generateInflows(JwtAuthenticationToken token,
                                                  LocalDate dataInicial,
                                                  LocalDate dataFinal){

        var valoresEntrada = transacaoRepository.findTransacaoByDateBetween(dataInicial,dataFinal )
                .stream()
                .filter(transacao -> transacao.getUser().getId().equals(UUID.fromString(token.getName())))
                .map(transacao -> transacao.toBalanceDTO(transacao))
                .filter(balanceDTO -> balanceDTO.getType().getCodigo().equals("E"))
                .map(balanceDTO -> balanceDTO.getBalance())
                .collect(Collectors.summingDouble(BigDecimal::doubleValue));

        DecimalFormat formato = new DecimalFormat("#.##");
        var entradaFormatada = (formato.format(valoresEntrada).replace(",", "."));

        return ResponseEntity.ok(entradaFormatada);
    }

    public ResponseEntity<String> generateOutflows(JwtAuthenticationToken token,
                                                   LocalDate dataInicial,
                                                   LocalDate dataFinal){

        var valoresSaida = transacaoRepository.findTransacaoByDateBetween(dataInicial,dataFinal)
                .stream()
                .filter(transacao -> transacao.getUser().getId().equals(UUID.fromString(token.getName())))
                .map(transacao -> transacao.toBalanceDTO(transacao))
                .filter(balanceDTO -> balanceDTO.getType().getCodigo().equals("S"))
                .map(balanceDTO -> balanceDTO.getBalance())
                .collect(Collectors.summingDouble(BigDecimal::doubleValue));

        DecimalFormat formato = new DecimalFormat("#.##");
        var saidaFormatada = (formato.format(valoresSaida).replace(",", "."));

        return ResponseEntity.ok(saidaFormatada);
    }

}
