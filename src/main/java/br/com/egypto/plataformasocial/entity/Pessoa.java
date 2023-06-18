package br.com.egypto.plataformasocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, name = "nickname")
    private String nickname;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, name = "senha")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "autoridade_id")
    private Autoridade autoridade;

    @Column(nullable = false, unique = true, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, unique = true, name = "modified_at")
    private OffsetDateTime modifiedAt;

    @Column(nullable = false, name = "ativo")
    private boolean ativo;
}
