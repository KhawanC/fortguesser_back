package br.com.egypto.plataformasocial.dto;

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

    private JogoCategoriaDto categoria;

    private DificuldadeDto dificuldade;

    private MultipartFile icone;
}
