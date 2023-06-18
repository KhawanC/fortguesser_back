package br.com.egypto.plataformasocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class JogoCategoria {

    @Id
    @SequenceGenerator(initialValue = 0, name = "jogo_categoria_seq", sequenceName = "jogo_categoria_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jogo_categoria_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, name = "nome")
    private String nome;

    @Column(nullable = false, name = "ativo")
    private boolean ativo;

    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, name = "modified_at")
    private OffsetDateTime modifiedAt;
}
