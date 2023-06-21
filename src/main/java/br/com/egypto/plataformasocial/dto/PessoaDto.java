package br.com.egypto.plataformasocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private UUID id;

    private String email;

    private String nickname;

    @JsonIgnore
    private String senha;

    private String nome;

    private AutoridadeDto autoridade;

    private boolean ativo;

    @JsonProperty
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
