package br.com.egypto.plataformasocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlataformaSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaSocialApplication.class, args);
	}

}
