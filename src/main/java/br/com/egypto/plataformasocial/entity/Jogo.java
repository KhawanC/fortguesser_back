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
public class Jogo {

    @Id
    @SequenceGenerator(initialValue = 0, name = "jogo_seq", sequenceName = "jogo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jogo_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, name = "nome")
    private String nome;

    @Column(nullable = false, name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "ativo")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private JogoCategoria categoria;

    @ManyToOne
    @JoinColumn(name = "dificuldade_id")
    private Dificuldade dificuldade;

    @Column(name = "icone")
    private byte[] icone;

    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, name = "modified_at")
    private OffsetDateTime modifiedAt;
}
