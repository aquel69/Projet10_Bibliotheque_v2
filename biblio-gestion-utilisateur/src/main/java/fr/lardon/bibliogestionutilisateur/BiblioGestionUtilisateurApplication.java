package fr.lardon.bibliogestionutilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
/**
 * classe permettant le démarrage du microservice gestion utilisateur
 */
public class BiblioGestionUtilisateurApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BiblioGestionUtilisateurApplication.class, args);
	}

}
