package br.com.egypto.plataformasocial.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String email;
    private String senha;
}
