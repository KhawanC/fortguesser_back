package br.com.egypto.plataformasocial.controller;

import br.com.egypto.plataformasocial.dto.JogoDtoRequest;
import br.com.egypto.plataformasocial.dto.JogoDtoResponse;
import br.com.egypto.plataformasocial.service.JogoService;
import br.com.egypto.plataformasocial.utils.JogoMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/jogo", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
@Validated
public class JogoController {

    @Autowired
    JogoService jogoService;

    @Autowired
    JogoMapper mapper;

    @GetMapping(value = "/buscarTodos")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Page<JogoDtoResponse>> buscarTodosJogos(Pageable pageable) {
        Page<JogoDtoResponse> jogoDtoResponses = jogoService.buscarTodos(pageable).map(entity ->
            mapper.toJogoDtoResponse(entity));
        return new ResponseEntity<>(jogoDtoResponses, HttpStatus.OK);
    }

    @GetMapping(value = "buscarByAtivo")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Page<JogoDtoResponse>> buscarTodosJogosByAtivo(Pageable pageable,
                                                                    @RequestParam(value = "ativo") boolean ativo) {
        Page<JogoDtoResponse> jogoDtoResponses = jogoService.buscarTodosByAtivo(pageable, ativo).map(enity ->
                mapper.toJogoDtoResponse(enity));
        return new ResponseEntity<>(jogoDtoResponses, HttpStatus.OK);
    }

    @PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<JogoDtoResponse> salvarJogo(JogoDtoRequest jogoDtoRequest) throws IOException {
        return new ResponseEntity<>(mapper.toJogoDtoResponse(jogoService.salvar(mapper.toJogo(jogoDtoRequest))), HttpStatus.OK);
    }

    @PatchMapping(value = "/atualizarImagem", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> atualizarIconeJogo(@RequestPart("image_file") MultipartFile image, @RequestParam(value = "id") Long id) throws IOException {
        jogoService.atualizarImagem(id, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
