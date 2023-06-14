package br.com.egypto.plataformasocial.security;

import br.com.egypto.plataformasocial.entity.Pessoa;
import br.com.egypto.plataformasocial.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaDetailsService implements UserDetailsService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Pessoa> pessoa = repository.findByEmail(username);
        return pessoa.map(PessoaDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

}
