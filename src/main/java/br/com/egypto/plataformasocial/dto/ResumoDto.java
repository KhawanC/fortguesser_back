package br.com.egypto.plataformasocial.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ResumoDto {

    private Long id;

    private Long jogosTotais;

    private Long jogosGanhos;

    private Long jogosPerdidos;

    private Long questoesErros;

    private Long streakVitoria;

    private UUID pessoaId;
}
