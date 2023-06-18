package br.com.egypto.plataformasocial.entity;

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
public class Autoridade {

    @Id
    @SequenceGenerator(initialValue = 0, name = "autoridade_seq", sequenceName = "autoridade_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "autoridade_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, name = "nome")
    private String nome;

    @Column(nullable = false, unique = true, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, unique = true, name = "modified_at")
    private OffsetDateTime modifiedAt;

    @Column(nullable = false, name = "ativo")
    private boolean ativo;
}
