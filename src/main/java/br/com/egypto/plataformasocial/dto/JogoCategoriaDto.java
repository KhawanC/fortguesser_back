package br.com.egypto.plataformasocial.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JogoCategoriaDto {

    private Long id;

    private String nome;

    private boolean ativo;
}
