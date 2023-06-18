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
public class Dificuldade {

    @Id
    @SequenceGenerator(initialValue = 0, name = "dificuldade_seq", sequenceName = "dificuldade_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dificuldade_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, name = "nome")
    private String nome;

    @Column(nullable = false, name = "quantidade_vidas")
    private int quantidadeVidas;

    @Column(nullable = false, name = "tempo_maximo")
    private int tempoMaximo; // Em segundos

    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, name = "modified_at")
    private OffsetDateTime modifiedAt;
}
