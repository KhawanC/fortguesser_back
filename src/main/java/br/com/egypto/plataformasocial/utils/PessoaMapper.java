package br.com.egypto.plataformasocial.utils;

import br.com.egypto.plataformasocial.dto.AutoridadeDto;
import br.com.egypto.plataformasocial.dto.PessoaDto;
import br.com.egypto.plataformasocial.entity.Autoridade;
import br.com.egypto.plataformasocial.entity.Pessoa;
import br.com.egypto.plataformasocial.security.PessoaDetails;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    public PessoaDto toPessoaDto(Pessoa entity) {
        return PessoaDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .nome(entity.getNome())
                .senha(entity.getSenha())
                .autoridade(AutoridadeDto.builder()
                        .id(entity.getAutoridade().getId())
                        .nome(entity.getAutoridade().getNome())
                        .ativo(entity.getAutoridade().isAtivo())
                        .build())
                .ativo(entity.isAtivo())
                .build();
    }

    public Pessoa toPessoa(PessoaDto dto) {
        return Pessoa.builder()
                .id(dto.getId())
                .nickname(dto.getNickname())
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .autoridade(Autoridade.builder()
                        .id(dto.getAutoridade().getId())
                        .nome(dto.getAutoridade().getNome())
                        .ativo(dto.getAutoridade().isAtivo())
                        .build())
                .ativo(dto.isAtivo())
                .build();
    }

    public Pessoa toPessoa(PessoaDetails details) {
        return Pessoa.builder()
                .id(details.getId())
                .email(details.getEmail())
                .senha(details.getSenha())
                .ativo(details.isAtivo())
                .build();
    }
}
