package br.com.egypto.plataformasocial.security;

import br.com.egypto.plataformasocial.entity.Pessoa;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.util.Objects.isNull;

@Component
public class JwtService {

    @Value("${api.security.token.secret}")
    public String jwtSecret;

    public String gerarToken(Pessoa pessoa) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withIssuer("API Plataforma Social")
                    .withSubject(pessoa.getEmail())
                    .withClaim("id", pessoa.getId().toString())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*30))
                    .sign(algoritmo);
        } catch (JWTCreationException ex) {
            throw new JWTCreationException(ex.getMessage(), ex.getCause());
        }
    }

    public String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return isNull(authHeader) ? null : authHeader.replace("Bearer ", "").trim();
    }

    public String getSubject(String tokenJwt) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algoritmo)
                    .withIssuer("API Plataforma Social")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException ex){
            throw new JWTVerificationException(ex.getMessage(), ex.getCause());
        }
    }
}
