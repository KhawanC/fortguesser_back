package br.com.egypto.plataformasocial.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ExceptionResponseDto {

    private UUID id;
    private HttpStatus status;
    private String mensagem;
    private OffsetDateTime dataErro;
}
