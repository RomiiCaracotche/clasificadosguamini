package com.clasificados.clasificadosGuamini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClasificadosGuaminiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClasificadosGuaminiApplication.class, args);
	}

}
