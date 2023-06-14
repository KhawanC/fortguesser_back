package br.com.egypto.plataformasocial.enums;

import lombok.Getter;

@Getter
public enum AutoridadeEnum {
    COMUM("COMUM"),
    MODERADOR("MODERADOR"),
    ADMINISTRADOR("ADMINISTRADOR");

    AutoridadeEnum(String nome) {
        this.nome = nome;
    }

    private String nome;
}
