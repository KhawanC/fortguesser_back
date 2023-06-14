package br.com.egypto.plataformasocial.repository;

import br.com.egypto.plataformasocial.entity.Autoridade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoridadeRepository extends CrudRepository<Autoridade, Long> {
    Optional<Autoridade> findByNome(String email);
}
