package br.com.egypto.plataformasocial.controller;

import br.com.egypto.plataformasocial.client.fortnite.controller.FortniteClient;
import br.com.egypto.plataformasocial.client.fortnite.entity.Root;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fortnite")
@Validated
public class FortniteController {

    @Autowired
    FortniteClient fortniteClient;

    @GetMapping()
    @Headers("Authorization: ${FORTNITE_SECRET}")
    public ResponseEntity<Root> buscarPorSearch(@RequestParam(name = "id") String id) {
        return new ResponseEntity<>(fortniteClient.getCosmeticsBySearch(id, "pt-BR"), HttpStatus.OK);
    }
}