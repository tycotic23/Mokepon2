package com.mokepon.mokepon;

import com.mokepon.mokepon.models.Cookie;
import com.mokepon.mokepon.repositories.CookieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MokeponApplication {

	public static void main(String[] args) {
		SpringApplication.run(MokeponApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(CookieRepository cookieRepository) {
		return (args -> {
			cookieRepository.save(new Cookie("Oreo","Tiene relleno de crema sabor vainilla y salta muy alto",35));
			cookieRepository.save(new Cookie("Pepa","¿Batata o membrillo? Podría ser cualquiera. La más resistente de todas.",40));
			cookieRepository.save(new Cookie("Con chispas","Gran poder de ataque, algo inestable, puede explotar.",30));
		});
	}

}
