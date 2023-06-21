package br.com.egypto.plataformasocial.service;

import br.com.egypto.plataformasocial.entity.Resumo;
import br.com.egypto.plataformasocial.repository.ResumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ResumoService {

    @Autowired
    ResumoRepository repository;

    @Autowired
    PessoaService pessoaService;

    public Resumo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resumo não encontrado com o id " + id));
    }

    public Resumo buscarPorPessoaId(String pessoaId) {
        return repository.findByPessoa_Id(UUID.fromString(pessoaId))
                .orElseThrow(() -> new NoSuchElementException("Resumo não encontrado para a pessoa com o id " + pessoaId));
    }


    public Resumo salvar(Resumo resumo) {
        resumo.setId(null);
        resumo.setCreatedAt(OffsetDateTime.now());
        resumo.setModifiedAt(OffsetDateTime.now());
        resumo.setPessoa(null);
        return repository.save(resumo);
    }

    public Resumo atualizar(Resumo resumo) {
        this.buscarPorId(resumo.getId());
        resumo.setModifiedAt(OffsetDateTime.now());
        return repository.save(resumo);
    }

    public Resumo vincularPessoaPorId(String idPessoa, Long resumoId) {
        Resumo resumo = this.buscarPorId(resumoId);
        resumo.setPessoa(pessoaService.buscarPorId(idPessoa));
        resumo.setModifiedAt(OffsetDateTime.now());
        return repository.save(resumo);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}
