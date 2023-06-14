package br.com.egypto.plataformasocial.utils;

import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class Utilities {

    public static final String EMAIL_REQUERIDO_ATUALIZAR = "É necessário fornecer o email para atualizar a pessoa";

    public void verificarExistenciaCampo(Object campo, String mensagem) {
        requireNonNull(campo, mensagem);
    }
}
