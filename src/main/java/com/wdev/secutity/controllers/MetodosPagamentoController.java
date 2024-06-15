package com.wdev.secutity.controllers;

import com.wdev.secutity.entities.MetodosPagamento;
import com.wdev.secutity.services.MetodosPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetodosPagamentoController {

    private final MetodosPagamentoService metodosPagamentoService;

    public MetodosPagamentoController(MetodosPagamentoService metodosPagamentoService) {
        this.metodosPagamentoService = metodosPagamentoService;
    }

    @Transactional
    @PostMapping("/metodo")
    public ResponseEntity<Void> saveMetodo(@RequestBody String name){
        return metodosPagamentoService.createMetodo(name);
    }

    @Transactional
    @GetMapping("/metodos")
    public ResponseEntity<List<MetodosPagamento>> listarMetodos(JwtAuthenticationToken token){
        return metodosPagamentoService.listarMetodos(token);
    }

}
