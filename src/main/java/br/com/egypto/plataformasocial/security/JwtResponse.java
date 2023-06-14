package br.com.egypto.plataformasocial.security;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
}
