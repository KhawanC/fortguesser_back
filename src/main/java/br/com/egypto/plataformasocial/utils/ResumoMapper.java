package br.com.egypto.plataformasocial.utils;

import br.com.egypto.plataformasocial.dto.ResumoDto;
import br.com.egypto.plataformasocial.entity.Pessoa;
import br.com.egypto.plataformasocial.entity.Resumo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.*;

@Component
public class ResumoMapper {

    @Autowired
    ModelMapper mapper;

    public ResumoDto toResumoDto(Resumo entity) {
        ResumoDto resumoDto = mapper.map(entity, ResumoDto.class);
        if (nonNull(entity.getPessoa()))
            resumoDto.setPessoaId(entity.getPessoa().getId());
        return  resumoDto;
    }

    public Resumo toResumo(ResumoDto dto) {
        return Resumo.builder()
                .id(dto.getId())
                .jogosPerdidos(dto.getJogosPerdidos())
                .jogosGanhos(dto.getJogosGanhos())
                .jogosTotais(dto.getJogosTotais())
                .streakVitorias(dto.getStreakVitorias())
                .questoesErros(dto.getQuestoesErros())
                .pessoa(Pessoa.builder()
                        .id(dto.getPessoaId())
                        .build())
                .build();
    }
}
