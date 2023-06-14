package br.com.egypto.plataformasocial.security;

import br.com.egypto.plataformasocial.entity.Pessoa;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
public class PessoaDetails implements UserDetails {

    private UUID id;
    private String email;
    private String senha;
    private boolean ativo;
    private List<GrantedAuthority> authorities;

    public PessoaDetails(Pessoa pessoa) {
        id = pessoa.getId();
        email = pessoa.getEmail();
        senha = pessoa.getSenha();
        ativo = pessoa.isAtivo();
        authorities = Stream.of(pessoa.getAutoridade().getNome())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
