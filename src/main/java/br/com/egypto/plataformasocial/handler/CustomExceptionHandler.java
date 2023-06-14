package br.com.egypto.plataformasocial.handler;

import br.com.egypto.plataformasocial.exception.ExceptionResponseDto;
import br.com.egypto.plataformasocial.exception.TokenInternalFilterException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponseDto handleNoSuchElementFoundException(NoSuchElementException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.NOT_FOUND)
                .mensagem(ex.getMessage())
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponseDto handleNullPointerException(NullPointerException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.NOT_FOUND)
                .mensagem(ex.getMessage())
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponseDto handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.UNAUTHORIZED)
                .mensagem("Credênciais inválidas")
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponseDto handleBadCredentialsException(BadCredentialsException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.UNAUTHORIZED)
                .mensagem("Credênciais inválidas")
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionResponseDto handleDisableException(DisabledException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.FORBIDDEN)
                .mensagem("Usuário desabilitado")
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponseDto handleSQLException(SQLException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.NOT_FOUND)
                .mensagem("Houve um problema na persistência do banco de dados: " + ex.getMessage())
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponseDto handleIllegalArgumentException(IllegalArgumentException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.BAD_REQUEST)
                .mensagem(ex.getMessage())
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(TokenInternalFilterException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponseDto handleTokenInternalFilterException(TokenInternalFilterException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.UNAUTHORIZED)
                .mensagem(ex.getMessage())
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(JWTCreationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponseDto handleJWTCreationException(JWTCreationException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.UNAUTHORIZED)
                .mensagem(ex.getMessage() + " | " + ex.getCause())
                .dataErro(OffsetDateTime.now()).build();
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponseDto handleJWTVerificationException(JWTVerificationException ex) {
        ex.printStackTrace();
        return ExceptionResponseDto.builder().id(UUID.randomUUID())
                .status(HttpStatus.UNAUTHORIZED)
                .mensagem(ex.getMessage() + " | " + ex.getCause())
                .dataErro(OffsetDateTime.now()).build();
    }
}
