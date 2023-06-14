package br.com.egypto.plataformasocial.service;

import br.com.egypto.plataformasocial.dto.PessoaDto;
import br.com.egypto.plataformasocial.entity.Autoridade;
import br.com.egypto.plataformasocial.entity.Pessoa;
import br.com.egypto.plataformasocial.enums.AutoridadeEnum;
import br.com.egypto.plataformasocial.repository.PessoaRepository;
import br.com.egypto.plataformasocial.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import static java.util.Objects.*;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    @Autowired
    AutoridadeService autoridadeService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    Utilities utilities;

    public Pessoa buscarPorId(String uuid) {
        return repository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada com o id " + uuid));
    }

    public Pessoa buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada com o email " + email));
    }

    public Pessoa buscarPorEmailOrId(String uuid, String email) {
        if(nonNull(uuid) && nonNull(email))
            throw new IllegalArgumentException("É necessário fornecer apenas o email ou o id do usuário");
        if(nonNull(uuid))
            return buscarPorId(uuid);
        else
            return buscarPorEmail(email);
    }

    public Pessoa salvar(Pessoa pessoa) {
        pessoa.setId(null);
        pessoa.setAutoridade(autoridadeService.buscarPorNome(String.valueOf(AutoridadeEnum.COMUM)));
        pessoa.setSenha(encoder.encode(pessoa.getSenha()));
        pessoa.setAtivo(true);
        pessoa.setCreatedAt(OffsetDateTime.now());
        pessoa.setModifiedAt(OffsetDateTime.now());
        pessoa.setNickname(pessoa.getNickname().toLowerCase());

        return repository.save(pessoa);
    }

    public Pessoa atualizarDadosGeraisPessoa(PessoaDto pessoa) {
        utilities.verificarExistenciaCampo(pessoa.getEmail(), Utilities.EMAIL_REQUERIDO_ATUALIZAR);
        Pessoa entity = buscarPorEmail(pessoa.getEmail());
        entity.setModifiedAt(OffsetDateTime.now());
        entity.setNickname(pessoa.getNickname());
        entity.setNome(pessoa.getNome());

        return repository.save(entity);
    }

    public Pessoa alterarStatusAtivacaoUsuarioPorEmail(String email, String senha) {
        utilities.verificarExistenciaCampo(email, Utilities.EMAIL_REQUERIDO_ATUALIZAR);
        Pessoa entity = buscarPorEmail(email);
        encoder.matches(senha, entity.getSenha());
        entity.setAtivo(!entity.isAtivo());

        return repository.save(entity);
    }

    public Pessoa alterarSenhaUsuarioPorEmail(String email, String senhaAntiga, String confirmacaoSenhaAntiga, String senhaNova) {
        utilities.verificarExistenciaCampo(email, Utilities.EMAIL_REQUERIDO_ATUALIZAR);

        if(!senhaAntiga.equals(confirmacaoSenhaAntiga))
            throw new IllegalArgumentException("Senhas não coincidem!");
        Pessoa entity = buscarPorEmail(email);
        encoder.matches(senhaAntiga, entity.getSenha());
        entity.setSenha(encoder.encode(senhaNova));

        return repository.save(entity);
    }

    public Pessoa alterarAutoridadeUsuarioPorEmail(String email, AutoridadeEnum autoridadeNome) {
        utilities.verificarExistenciaCampo(email, Utilities.EMAIL_REQUERIDO_ATUALIZAR);

        Autoridade autoridade = autoridadeService.buscarPorNome(autoridadeNome.getNome());
        Pessoa entity = buscarPorEmail(email);
        entity.setAutoridade(autoridade);

        return repository.save(entity);
    }
}
