package com.wdev.secutity.controllers;

import com.wdev.secutity.dtos.CreateTransDTO;
import com.wdev.secutity.entities.Transacao;
import com.wdev.secutity.repositories.TransacaoRepository;
import com.wdev.secutity.repositories.UserRepository;
import com.wdev.secutity.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final UserRepository userRepository;
    private final TransacaoRepository transacaoRepository;


    public TransacaoController(TransacaoService transacaoService, UserRepository userRepository, TransacaoRepository transacaoRepository) {
        this.transacaoService = transacaoService;
        this.userRepository = userRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    @PostMapping("/trans")
    public ResponseEntity<Void> createTrans(@RequestBody CreateTransDTO dto,
                                            JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var transacao = new Transacao();
        transacao.setDescription(dto.getDescription());
        transacao.setUser(user.get());
        transacao.setPrice(dto.getPrice());
        transacao.setCategory(dto.getCategory());
        transacao.setType(dto.getType());

        transacaoRepository.save(transacao);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @GetMapping("/trans/{id}")
    public ResponseEntity<CreateTransDTO> findTransacaoId(@PathVariable("id") Long transId, JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));
        var transacao = transacaoRepository.findById(transId);


        if (user.isEmpty()) {

            return ResponseEntity.notFound().build();

        } else if (transacao.get().getUser().getId().equals(UUID.fromString(token.getName()))) {

            return ResponseEntity.ok(transacao.get().modelToDTO(transacao.get()));

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Transactional
    @PatchMapping("/updatetrans/{id}")
    public ResponseEntity<Void> updateTransacao(@RequestBody CreateTransDTO dto,
                                                JwtAuthenticationToken token,
                                                @PathVariable("id") Long transId) {

        var user = userRepository.findById(UUID.fromString(token.getName()));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var transacao = transacaoRepository.findById(transId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (transacao.getUser().getId().equals(UUID.fromString(token.getName()))) {
            transacao.setDescription(dto.getDescription());
            transacao.setUser(user.get());
            transacao.setPrice(dto.getPrice());
            transacao.setCategory(dto.getCategory());
            transacao.setType(dto.getType());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        transacaoRepository.save(transacao);
        return ResponseEntity.ok().build();

    }

    @Transactional
    @GetMapping("/trans")
    public ResponseEntity listarTransacoes(JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var list = transacaoRepository.findAll()
                .stream()
                .filter(transacao -> transacao.getUser().getId().equals(UUID.fromString(token.getName())))
                .map(transacao -> transacao.modelToDTO(transacao)).toList();

        return ResponseEntity.ok(list);
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
}
