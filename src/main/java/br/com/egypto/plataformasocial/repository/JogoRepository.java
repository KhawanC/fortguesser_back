package br.com.egypto.plataformasocial.repository;

import br.com.egypto.plataformasocial.entity.Jogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface JogoRepository extends CrudRepository<Jogo, Long> {

    @Transactional
    @Modifying
    @Query("update Jogo j set j.icone = ?1 where j.id = ?2")
    void updateIconeById(byte[] icone, Long id);

    Page<Jogo> findByAtivo(boolean ativo, Pageable pageable);

    Page<Jogo> findAll(Pageable pageable);
}
