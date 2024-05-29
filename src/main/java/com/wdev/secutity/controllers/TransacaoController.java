package com.wdev.secutity.controllers;

import com.wdev.secutity.dtos.CreateTransDTO;
import com.wdev.secutity.repositories.TransacaoRepository;
import com.wdev.secutity.repositories.UserRepository;
import com.wdev.secutity.services.BalanceService;
import com.wdev.secutity.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;


@RestController
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final BalanceService balanceService;

    public TransacaoController(TransacaoService transacaoService, BalanceService balanceService) {
        this.transacaoService = transacaoService;
        this.balanceService = balanceService;
    }

    @Transactional
    @PostMapping("/trans")
    public ResponseEntity<Void> createTrans(@RequestBody CreateTransDTO dto, JwtAuthenticationToken token) {
        return transacaoService.createTrans(dto,token);
    }

    @Transactional
    @GetMapping("/trans/{id}")
    public ResponseEntity<CreateTransDTO> findTransacaoId(@PathVariable("id") Long transId, JwtAuthenticationToken token) {
        return transacaoService.findTransacaoId(transId, token);
    }

    @Transactional
    @PatchMapping("/updatetrans/{id}")
    public ResponseEntity<Void> updateTransacao(@RequestBody CreateTransDTO dto, JwtAuthenticationToken token,
                                                @PathVariable("id") Long transId) {

     return transacaoService.updateTransacao(dto,token,transId);
    }

    @Transactional
    @GetMapping("/trans")
    public ResponseEntity<List<CreateTransDTO>> listarTransacoes(JwtAuthenticationToken token) {

        return transacaoService.listarTransacoes(token);
    }

    @Transactional
    @DeleteMapping("/trash/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable("id") Long transId, JwtAuthenticationToken token) {

        var transacao = transacaoRepository.findById(transId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (transacao.getUser().getId().equals(UUID.fromString(token.getName()))) {
            transacaoRepository.deleteById(transId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<Double>  pegarBalance(JwtAuthenticationToken token){
        return balanceService.generateBalance(token);
    }

}
