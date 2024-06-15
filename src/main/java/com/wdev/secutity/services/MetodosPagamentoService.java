package com.wdev.secutity.services;

import com.wdev.secutity.entities.MetodosPagamento;
import com.wdev.secutity.repositories.MetodosPagamentoRepository;
import com.wdev.secutity.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MetodosPagamentoService {

    private final MetodosPagamentoRepository metodosPagamentoRepository;
    private final UserRepository userRepository;

    public MetodosPagamentoService(MetodosPagamentoRepository metodosPagamentoRepository, UserRepository userRepository) {
        this.metodosPagamentoRepository = metodosPagamentoRepository;
        this.userRepository = userRepository;

    }

    @Transactional
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> createMetodo(String name){

        var metodo = metodosPagamentoRepository.findByName(name);

        if (metodo.getName().isEmpty()) {
            var newMetodo = new MetodosPagamento();
            newMetodo.setName(name);
            metodosPagamentoRepository.save(newMetodo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<List<MetodosPagamento>> listarMetodos(JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var list = metodosPagamentoRepository.findAll();
        return ResponseEntity.ok(list);
    }

}
