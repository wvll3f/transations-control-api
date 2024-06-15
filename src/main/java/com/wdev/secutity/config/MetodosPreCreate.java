package com.wdev.secutity.config;

import com.wdev.secutity.entities.MetodosPagamento;
import com.wdev.secutity.repositories.MetodosPagamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetodosPreCreate implements CommandLineRunner {

    private final MetodosPagamentoRepository metodosPagamentoRepository;

    public MetodosPreCreate(MetodosPagamentoRepository metodosPagamentoRepository) {
        this.metodosPagamentoRepository = metodosPagamentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var metodos = metodosPagamentoRepository.findAll();

        if (metodos.isEmpty()) {
            var credito = new MetodosPagamento();
            credito.setName("Credito");
            metodosPagamentoRepository.save(credito);

            var debito = new MetodosPagamento();
            debito.setName("Debito");
            metodosPagamentoRepository.save(debito);

            var pix = new MetodosPagamento();
            pix.setName("Pix");
            metodosPagamentoRepository.save(pix);

            var dinheiro = new MetodosPagamento();
            dinheiro.setName("Dinheiro");
            metodosPagamentoRepository.save(dinheiro);

            var pixCredito = new MetodosPagamento();
            pixCredito.setName("Pix no credito");
            metodosPagamentoRepository.save(pixCredito);

        } else {
            System.out.println("Categorias já criadas");
        }

    }
}
