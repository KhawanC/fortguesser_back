package br.com.egypto.plataformasocial.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DificuldadeDto {

    private Long id;

    private String nome;

    private int quantidadeVidas;

    private int tempoMaximo;
}
