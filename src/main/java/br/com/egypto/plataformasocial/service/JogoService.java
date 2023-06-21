package br.com.egypto.plataformasocial.service;

import br.com.egypto.plataformasocial.dto.JogoDtoRequest;
import br.com.egypto.plataformasocial.entity.Jogo;
import br.com.egypto.plataformasocial.repository.JogoRepository;
import br.com.egypto.plataformasocial.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Objects.*;

@Service
public class JogoService {

    @Autowired
    JogoRepository repository;

    @Autowired
    Utilities utils;

    public Page<Jogo> buscarTodos(Pageable pageable) {
        List<Jogo> jogosList =  repository.findAll(pageable).stream().peek(entity -> {
            if (nonNull(entity.getIcone())) {
                entity.setIcone(utils.decompressImage(entity.getIcone()));
            }
        }).toList();
        return new PageImpl<>(jogosList);
    }

    public Page<Jogo> buscarTodosByAtivo(Pageable pageable, boolean ativo) {
        List<Jogo> jogosList =  repository.findByAtivo(ativo, pageable).stream().peek(entity -> {
            if (nonNull(entity.getIcone())) {
                entity.setIcone(utils.decompressImage(entity.getIcone()));
            }
        }).toList();
        return new PageImpl<>(jogosList);
    }

    public Jogo buscarPorId(Long id) {
        Jogo jogo = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Jogo não encontrado com o id " + id));
        jogo.setIcone(utils.decompressImage(jogo.getIcone()));
        return jogo;
    }

    public Jogo salvar(Jogo jogo) {
        jogo.setId(null);
        jogo.setIcone(nonNull(jogo.getIcone()) ? utils.compressImage(jogo.getIcone()) : null);
        jogo.setCreatedAt(OffsetDateTime.now());
        jogo.setModifiedAt(OffsetDateTime.now());
        Jogo jogoSalvo = repository.save(jogo);
        jogoSalvo.setIcone(utils.decompressImage(jogo.getIcone()));
        return jogoSalvo;
    }

    public void atualizarImagem(Long id, MultipartFile imagem) throws IOException {
        repository.updateIconeById(utils.compressImage(imagem.getBytes()), id);
    }

    public void deletarPorId(Long id) {
        repository.delete(this.buscarPorId(id));
    }
}
