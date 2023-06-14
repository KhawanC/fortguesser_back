package br.com.egypto.plataformasocial.repository;

import br.com.egypto.plataformasocial.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, UUID> {
    Optional<Pessoa> findByEmail(String email);
}
