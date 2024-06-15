package com.wdev.secutity.controllers;

import com.wdev.secutity.entities.Categoria;
import com.wdev.secutity.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Transactional
    @PostMapping("/categoria")
    public ResponseEntity<Void> saveCategory(@RequestBody String name) {
        return categoriaService.createCategory(name);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> listarCategorias(JwtAuthenticationToken token){
        return categoriaService.listarCategorias(token);
    }

}
