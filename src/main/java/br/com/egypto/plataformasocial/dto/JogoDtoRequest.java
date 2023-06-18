package br.com.egypto.plataformasocial.dto;

import br.com.egypto.plataformasocial.entity.Dificuldade;
import br.com.egypto.plataformasocial.entity.JogoCategoria;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JogoDtoRequest {

    private Long id;

    private String nome;

    private String descricao;

    private boolean ativo;

    private JogoCategoria categoria;

    private Dificuldade dificuldade;

    private MultipartFile icone;
}
