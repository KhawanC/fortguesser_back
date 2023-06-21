package br.com.egypto.plataformasocial.utils;

import br.com.egypto.plataformasocial.dto.DificuldadeDto;
import br.com.egypto.plataformasocial.dto.JogoCategoriaDto;
import br.com.egypto.plataformasocial.dto.JogoDtoRequest;
import br.com.egypto.plataformasocial.dto.JogoDtoResponse;
import br.com.egypto.plataformasocial.entity.Dificuldade;
import br.com.egypto.plataformasocial.entity.Jogo;
import br.com.egypto.plataformasocial.entity.JogoCategoria;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JogoMapper {

    public JogoDtoResponse toJogoDtoResponse(Jogo entity) {
        return JogoDtoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .ativo(entity.isAtivo())
                .categoria(JogoCategoriaDto.builder()
                        .id(entity.getCategoria().getId())
                        .nome(entity.getCategoria().getNome())
                        .ativo(entity.getCategoria().isAtivo())
                        .build())
                .dificuldade(DificuldadeDto.builder()
                        .id(entity.getDificuldade().getId())
                        .nome(entity.getDificuldade().getNome())
                        .quantidadeVidas(entity.getDificuldade().getQuantidadeVidas())
                        .tempoMaximo(entity.getDificuldade().getTempoMaximo())
                        .build())
                .icone(entity.getIcone())
                .build();
    }

    public Jogo toJogo(JogoDtoRequest dtoRequest) throws IOException {
        return Jogo.builder()
                .id(dtoRequest.getId())
                .nome(dtoRequest.getNome())
                .ativo(dtoRequest.isAtivo())
                .categoria(JogoCategoria.builder()
                        .id(dtoRequest.getCategoria().getId())
                        .nome(dtoRequest.getCategoria().getNome())
                        .ativo(dtoRequest.getCategoria().isAtivo())
                        .build())
                .dificuldade(Dificuldade.builder()
                        .id(dtoRequest.getDificuldade().getId())
                        .nome(dtoRequest.getDificuldade().getNome())
                        .quantidadeVidas(dtoRequest.getDificuldade().getQuantidadeVidas())
                        .tempoMaximo(dtoRequest.getDificuldade().getTempoMaximo())
                        .build())
                .icone(dtoRequest.getIcone().getBytes())
                .build();
    }
}
