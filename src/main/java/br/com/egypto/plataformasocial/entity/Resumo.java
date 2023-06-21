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
public class Resumo {

    @Id
    @SequenceGenerator(initialValue = 0, name = "resumo_seq", sequenceName = "resumo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jogo_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, name = "jogos_totais")
    private Long jogosTotais;

    @Column(nullable = false, name = "jogos_ganhos")
    private Long jogosGanhos;

    @Column(nullable = false, name = "jogos_perdidos")
    private Long jogosPerdidos;

    @Column(nullable = false, name = "questoes_erros")
    private Long questoesErros;

    @Column(nullable = false, name = "streak_vitoria")
    private Long streakVitoria;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", unique = true)
    private Pessoa pessoa;

    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, name = "modified_at")
    private OffsetDateTime modifiedAt;
}
