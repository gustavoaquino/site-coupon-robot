package br.com.cupom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CupomApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupomApplication.class, args);
	}

}
