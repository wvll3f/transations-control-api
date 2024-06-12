package com.wdev.secutity.services;

import com.wdev.secutity.entities.Categoria;
import com.wdev.secutity.repositories.CategoriaRepository;
import com.wdev.secutity.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, UserRepository userRepository) {
        this.categoriaRepository = categoriaRepository;
        this.userRepository = userRepository;

    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> createCategory(String name){

        var categoria = categoriaRepository.findByName(name);

        if (categoria.getName().isEmpty()) {
            var newCategoria = new Categoria();
            newCategoria.setName(name);
            categoriaRepository.save(newCategoria);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<List<Categoria>> listarCategorias(JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var list = categoriaRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
