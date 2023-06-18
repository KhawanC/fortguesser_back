package br.com.egypto.plataformasocial.client.fortnite.controller;

import br.com.egypto.plataformasocial.client.fortnite.entity.Root;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://fortnite-api.com/v2", name = "fortnite-integration")
public interface FortniteClient {

    @GetMapping(value = "/cosmetics/br/search", params = {"id", "language"})
    Root getCosmeticsBySearch(@RequestParam("id") String id, @RequestParam("language") String language);
}
