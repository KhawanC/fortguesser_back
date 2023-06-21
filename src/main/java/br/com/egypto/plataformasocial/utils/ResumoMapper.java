package br.com.egypto.plataformasocial.utils;

import br.com.egypto.plataformasocial.dto.ResumoDto;
import br.com.egypto.plataformasocial.entity.Pessoa;
import br.com.egypto.plataformasocial.entity.Resumo;
import org.springframework.stereotype.Component;

@Component
public class ResumoMapper {

    public ResumoDto toResumoDto(Resumo entity) {
        return ResumoDto.builder()
                .id(entity.getId())
                .jogosTotais(entity.getJogosTotais())
                .jogosGanhos(entity.getJogosGanhos())
                .jogosPerdidos(entity.getJogosPerdidos())
                .questoesErros(entity.getQuestoesErros())
                .streakVitoria(entity.getStreakVitoria())
                .pessoaId(entity.getPessoa().getId())
                .build();
    }

    public Resumo toResumo(ResumoDto dto) {
        return Resumo.builder()
                .id(dto.getId())
                .jogosPerdidos(dto.getJogosPerdidos())
                .jogosGanhos(dto.getJogosGanhos())
                .jogosTotais(dto.getJogosTotais())
                .streakVitoria(dto.getStreakVitoria())
                .questoesErros(dto.getQuestoesErros())
                .pessoa(Pessoa.builder()
                        .id(dto.getPessoaId())
                        .build())
                .build();
    }
}
