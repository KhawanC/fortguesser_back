package br.com.egypto.plataformasocial.repository;

import br.com.egypto.plataformasocial.entity.Resumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResumoRepository extends CrudRepository<Resumo, Long> {
    Optional<Resumo> findByPessoa_Id(UUID id);
}
