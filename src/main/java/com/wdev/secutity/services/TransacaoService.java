package com.wdev.secutity.services;

import com.wdev.secutity.entities.Transacao;
import com.wdev.secutity.repositories.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public Transacao findById(Long id) {
        return transacaoRepository.findById(id).get();
    }

    @Transactional
    public List<Transacao> listarTransacao() {
        List<Transacao> listatrans = transacaoRepository.findAll();
        return listatrans;
    }

    @Transactional
    public Transacao salvarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }


    @Transactional
    public void deletarTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

}
