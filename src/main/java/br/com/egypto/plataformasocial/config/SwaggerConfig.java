package br.com.egypto.plataformasocial.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Plataforma Social",
                version = "${build.version}",
                contact = @Contact(
                        name = "Kauã Cassiano", email = "kauacassiano121@gmail.com"
                ),
                license = @License(
                        name = "None"
                ),
                description = "${build.description}"
        ), servers = {
            @Server(
                url = "http://localhost:${server.port}${server.servlet.context-path}",
                description = "Servidor Local"
            ), @Server(
                url = "${PRODUCTION_API_URL}${server.servlet.context-path}",
                description = "Podução - Railway"
            )}
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}
