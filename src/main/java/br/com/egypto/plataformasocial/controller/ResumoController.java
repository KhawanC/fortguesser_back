package br.com.egypto.plataformasocial.controller;

import br.com.egypto.plataformasocial.dto.ResumoDto;
import br.com.egypto.plataformasocial.entity.Resumo;
import br.com.egypto.plataformasocial.service.ResumoService;
import br.com.egypto.plataformasocial.utils.ResumoMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    ResumoService resumoService;

    @Autowired
    ResumoMapper mapper;

    @GetMapping("buscarPorId/${id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ResumoDto> buscarResumoPorId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(mapper.toResumoDto(resumoService.buscarPorId(id)), HttpStatus.OK);
    }

    @GetMapping("buscarPorPessoaId/${pessoaId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ResumoDto> buscarResumoPorPessoaId(@PathVariable(name = "pessoaId") String pessoaId) {
        return new ResponseEntity<>(mapper.toResumoDto(resumoService.buscarPorPessoaId(pessoaId)), HttpStatus.OK);
    }

    @PostMapping("salvar")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ResumoDto> salvarResumo(@RequestBody ResumoDto resumoDto) {
        return new ResponseEntity<>(mapper.toResumoDto(resumoService.salvar(mapper.toResumo(resumoDto))), HttpStatus.CREATED);
    }

    @PutMapping("atualizar")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ResumoDto> atualizarResumo(@RequestBody ResumoDto resumoDto) {
        return new ResponseEntity<>(mapper.toResumoDto(resumoService.atualizar(mapper.toResumo(resumoDto))), HttpStatus.OK);
    }

    @PatchMapping("vincularPessoaPorResumoId")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ResumoDto> atualizarPessoaDeResumoPorResumoId(@RequestParam(value = "resumoId") Long resumoId,
                                                                @RequestParam(value = "pessoaId") String pessoaId) {
        return new ResponseEntity<>(mapper.toResumoDto(resumoService.vincularPessoaPorId(pessoaId, resumoId)), HttpStatus.OK);
    }

    @DeleteMapping("deletar/${id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deletarResumo(@PathVariable(value = "id") Long resumoId) {
        resumoService.deletarPorId(resumoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
