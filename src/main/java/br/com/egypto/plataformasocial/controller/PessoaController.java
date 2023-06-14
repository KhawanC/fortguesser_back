package br.com.egypto.plataformasocial.controller;

import br.com.egypto.plataformasocial.dto.PessoaDto;
import br.com.egypto.plataformasocial.entity.Pessoa;
import br.com.egypto.plataformasocial.enums.AutoridadeEnum;
import br.com.egypto.plataformasocial.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoa")
@Validated
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    ModelMapper mapper;

    @GetMapping()
    @Operation(summary = "Buscar pessoa por email ou por id", description = "Passar apenas 1 dos valores")
    public ResponseEntity<PessoaDto> buscarPessoaPorEmailOrId(@RequestParam(name = "email", required = false) String email,
                                                              @RequestParam(name = "id", required = false) String id) {
        return new ResponseEntity<>(mapper.map(pessoaService.buscarPorEmailOrId(id, email), PessoaDto.class), HttpStatus.OK);
    }

    @PostMapping()
    @Operation(summary = "Registrar pessoa")
    public ResponseEntity<PessoaDto> salvarPessoa(@RequestBody Pessoa pessoa, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
        PessoaDto pessoaDto = mapper.map(pessoaService.salvar(pessoa), PessoaDto.class);
        response.setHeader("Location", uriBuilder.path("/pessoa?email={email}").buildAndExpand(pessoaDto.getEmail()).toUriString());
        return new ResponseEntity<>(pessoaDto, HttpStatus.CREATED);
    }

    @PutMapping("atualizar-dados-gerais")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Atualizar dados gerais", description = "Dados gerais por enquanto consideramos apenas nick, nome, imagem de perfil, descricao perfil, etc..")
    public ResponseEntity<PessoaDto> atualizarDadosGeraisPessoa(@RequestBody PessoaDto pessoa) {
        return new ResponseEntity<>(mapper.map(pessoaService.atualizarDadosGeraisPessoa(pessoa), PessoaDto.class), HttpStatus.OK);
    }

    @PatchMapping("alterar-senha")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Alterar senha")
    public ResponseEntity<PessoaDto> alterarSenhaPessoa(@RequestParam(name = "email") String email,
                                                    @RequestParam(name = "senhaAntiga") String senhaAntiga,
                                                    @RequestParam(name = "confirmacaoSenhaAntiga") String confirmacaoSenhaAntiga,
                                                    @RequestParam(name = "senhaNova") String senhaNova) {
        return new ResponseEntity<>(mapper.map(pessoaService
                .alterarSenhaUsuarioPorEmail(email, senhaAntiga, confirmacaoSenhaAntiga, senhaNova), PessoaDto.class), HttpStatus.OK);
    }

    @PatchMapping("alterar-autoridade")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Alterar autoridade")
    public ResponseEntity<PessoaDto> alterarAutoridadePessoa(@RequestParam(name = "email") String email,
                                                   @RequestParam(name = "autoridade") AutoridadeEnum autoridade) {
        return new ResponseEntity<>(mapper.map(pessoaService.alterarAutoridadeUsuarioPorEmail(email, autoridade), PessoaDto.class), HttpStatus.OK);
    }

    @PatchMapping("atualizar-status-ativacao")
    @Operation(summary = "Atualizar status de ativação", description = "Esse endpoint troca o status de ativação da conta, bloquenado algumas coisas." +
            "Por exemplo, contas desativas não podem realizar login")
    public ResponseEntity<PessoaDto> atualizarStatusAtivacaoPessoa(@RequestParam(name = "email") String email,
                                                              @RequestParam(name = "senha") String senha) {
        return new ResponseEntity<>(mapper.map(pessoaService.alterarStatusAtivacaoUsuarioPorEmail(email, senha), PessoaDto.class), HttpStatus.OK);
    }
}