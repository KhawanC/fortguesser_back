package br.com.egypto.plataformasocial.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private String email;

    private String nickname;

    private String nome;

    private AutoridadeDto autoridade;

    private boolean ativo;
}
