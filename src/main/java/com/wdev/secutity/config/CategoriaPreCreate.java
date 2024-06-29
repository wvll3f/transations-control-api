package com.wdev.secutity.config;

import com.wdev.secutity.entities.Categoria;
import com.wdev.secutity.repositories.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaPreCreate implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    public CategoriaPreCreate(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var categorias = categoriaRepository.findAll();

        if (categorias.isEmpty()) {
            var comida = new Categoria();
            comida.setName("Comida");
            categoriaRepository.save(comida);

            var mercado = new Categoria();
            mercado.setName("Mercado");
            categoriaRepository.save(mercado);

            var contas = new Categoria();
            contas.setName("Contas");
            categoriaRepository.save(contas);

            var compras = new Categoria();
            compras.setName("Compras");
            categoriaRepository.save(compras);

            var transporte = new Categoria();
            transporte.setName("Transporte");
            categoriaRepository.save(transporte);

            var saude = new Categoria();
            saude.setName("Saúde");
            categoriaRepository.save(saude);

            var salario = new Categoria();
            salario.setName("Salário");
            categoriaRepository.save(salario);

        } else {
            System.out.println("Categorias já criadas");
        }

    }
}
