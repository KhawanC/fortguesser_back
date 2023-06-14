package br.com.egypto.plataformasocial.service;

import br.com.egypto.plataformasocial.entity.Autoridade;
import br.com.egypto.plataformasocial.repository.AutoridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AutoridadeService {

    @Autowired
    private AutoridadeRepository repository;

    public Autoridade buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Autoridade não encontrada com o id " + id));
    }

    public Autoridade buscarPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new NoSuchElementException("Autoridade não encontrada com o nome " + nome));
    }
}
