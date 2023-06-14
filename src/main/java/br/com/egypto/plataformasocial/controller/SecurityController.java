package br.com.egypto.plataformasocial.controller;

import br.com.egypto.plataformasocial.dto.LoginRequest;
import br.com.egypto.plataformasocial.entity.Pessoa;
import br.com.egypto.plataformasocial.security.JwtResponse;
import br.com.egypto.plataformasocial.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/auth")
@Validated
public class SecurityController {

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    ModelMapper mapper;

    @PostMapping("/login")
    @Operation(summary = "Login")
    public JwtResponse login(@RequestBody LoginRequest authRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getSenha());
        Authentication authentication = manager.authenticate(authToken);
        return JwtResponse.builder()
                .token(jwtService.gerarToken(mapper.map(authentication.getPrincipal(), Pessoa.class)))
                .build();
    }
}
