package ec.edu.uteq.microservicios.msusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class MsUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUsuariosApplication.class, args);
	}

}
