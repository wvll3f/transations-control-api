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

            var internet = new Categoria();
            internet.setName("Internet");
            categoriaRepository.save(internet);

            var compras = new Categoria();
            compras.setName("Compras");
            categoriaRepository.save(compras);

            var energia = new Categoria();
            energia.setName("Energia");
            categoriaRepository.save(energia);

            var agua = new Categoria();
            agua.setName("Agua");
            categoriaRepository.save(agua);
        } else {
            System.out.println("Categorias já criadas");
        }

    }
}
