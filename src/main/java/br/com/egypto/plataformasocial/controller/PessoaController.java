package br.com.egypto.plataformasocial.controller;

import br.com.egypto.plataformasocial.dto.PessoaDto;
import br.com.egypto.plataformasocial.enums.AutoridadeEnum;
import br.com.egypto.plataformasocial.service.PessoaService;
import br.com.egypto.plataformasocial.utils.PessoaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
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
    PessoaMapper mapper;

    @GetMapping("buscarPorIdOuEmail")
    @Operation(summary = "Buscar pessoa por email ou por id", description = "Passar apenas 1 dos valores", security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<PessoaDto> buscarPessoaPorEmailOrId(@RequestParam(name = "email", required = false) String email,
                                                              @RequestParam(name = "id", required = false) String id) {
        return new ResponseEntity<>(mapper.toPessoaDto(pessoaService.buscarPorEmailOrId(id, email)), HttpStatus.OK);
    }

    @PostMapping("/salvar")
    @Operation(summary = "Registrar pessoa")
    public ResponseEntity<PessoaDto> salvarPessoa(@RequestBody PessoaDto pessoaDto, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
        PessoaDto pessoa = mapper.toPessoaDto(pessoaService.salvar(mapper.toPessoa(pessoaDto)));
        response.setHeader("Location", uriBuilder.path("/pessoa?email={email}").buildAndExpand(pessoa.getEmail()).toUriString());
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @PutMapping("atualizarDadosGerais")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Atualizar dados gerais", description = "Dados gerais por enquanto consideramos apenas nick, nome, imagem de perfil, descricao perfil, etc..")
    public ResponseEntity<PessoaDto> atualizarDadosGeraisPessoa(@RequestBody PessoaDto pessoaDto) {
        return new ResponseEntity<>(mapper.toPessoaDto(pessoaService.atualizarDadosGeraisPessoa(pessoaDto)), HttpStatus.OK);
    }

    @PatchMapping("alterarSenha")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Alterar senha")
    public ResponseEntity<PessoaDto> alterarSenhaPessoa(@RequestParam(name = "email") String email,
                                                    @RequestParam(name = "senhaAntiga") String senhaAntiga,
                                                    @RequestParam(name = "confirmacaoSenhaAntiga") String confirmacaoSenhaAntiga,
                                                    @RequestParam(name = "senhaNova") String senhaNova) {
        return new ResponseEntity<>(mapper.toPessoaDto(pessoaService
                .alterarSenhaUsuarioPorEmail(email, senhaAntiga, confirmacaoSenhaAntiga, senhaNova)), HttpStatus.OK);
    }

    @PatchMapping("alterarAutoridade")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Alterar autoridade")
    public ResponseEntity<PessoaDto> alterarAutoridadePessoa(@RequestParam(name = "email") String email,
                                                   @RequestParam(name = "autoridade") AutoridadeEnum autoridade) {
        return new ResponseEntity<>(mapper.toPessoaDto(pessoaService.alterarAutoridadeUsuarioPorEmail(email, autoridade)), HttpStatus.OK);
    }

    @PatchMapping("atualizarStatusAtivacao")
    @Operation(summary = "Atualizar status de ativação", description = "Esse endpoint troca o status de ativação da conta, bloquenado algumas coisas." +
            "Por exemplo, contas desativas não podem realizar login")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PessoaDto> atualizarStatusAtivacaoPessoa(@RequestParam(name = "email") String email,
                                                              @RequestParam(name = "senha") String senha) {
        return new ResponseEntity<>(mapper.toPessoaDto(pessoaService.alterarStatusAtivacaoUsuarioPorEmail(email, senha)), HttpStatus.OK);
    }
}