package com.mokepon.mokepon;

import com.mokepon.mokepon.models.Cookie;
import com.mokepon.mokepon.models.CookiePlayer;
import com.mokepon.mokepon.models.Player;
import com.mokepon.mokepon.repositories.CookiePlayerRepository;
import com.mokepon.mokepon.repositories.CookieRepository;
import com.mokepon.mokepon.repositories.PlayerRepository;
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
	public CommandLineRunner initData(CookieRepository cookieRepository, PlayerRepository playerRepository, CookiePlayerRepository cookiePlayerRepository) {
		return (args -> {

			Cookie cookieOreo=new Cookie("Oreo","Tiene relleno de crema sabor vainilla y salta muy alto",35);
			Cookie cookiePepa=new Cookie("Pepa","¿Batata o membrillo? Podría ser cualquiera. La más resistente de todas.",40);
			Cookie cookieChispas=new Cookie("Con chispas","Gran poder de ataque, algo inestable, puede explotar.",30);
			cookieRepository.save( cookieOreo);
			cookieRepository.save(cookiePepa);
			cookieRepository.save(cookieChispas);

			Player playerMain= new Player("stevan");
			CookiePlayer oreostevan=new CookiePlayer(cookieOreo);
			playerMain.setMonster(oreostevan);

			Player playerEnemy= new Player("Susan");
			CookiePlayer pepaSusan=new CookiePlayer(cookiePepa);
			playerEnemy.setMonster(pepaSusan);

			playerRepository.save(playerEnemy);
			playerRepository.save(playerMain);

			cookiePlayerRepository.save(oreostevan);
			cookiePlayerRepository.save(pepaSusan);





		});
	}

}
