package fr.lardon.biblioauthentificationutilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
/**
 * classe permettant le démarrage du microservice authentification
 */
public class BiblioAuthentificationUtilisateurApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BiblioAuthentificationUtilisateurApplication.class, args);

	}
}
