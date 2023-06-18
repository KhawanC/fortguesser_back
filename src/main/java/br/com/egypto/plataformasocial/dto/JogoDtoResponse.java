package br.com.egypto.plataformasocial.dto;

import br.com.egypto.plataformasocial.entity.Dificuldade;
import br.com.egypto.plataformasocial.entity.JogoCategoria;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JogoDtoResponse {

    private Long id;

    private String nome;

    private String descricao;

    private boolean ativo;

    private JogoCategoriaDto categoria;

    private DificuldadeDto dificuldade;

    private byte[] icone;
}
