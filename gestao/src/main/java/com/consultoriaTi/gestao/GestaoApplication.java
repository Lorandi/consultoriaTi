package com.consultoriaTi.gestao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class GestaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoApplication.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

}
