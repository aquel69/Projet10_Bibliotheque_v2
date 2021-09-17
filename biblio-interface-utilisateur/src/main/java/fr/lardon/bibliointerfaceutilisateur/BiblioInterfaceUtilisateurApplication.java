package fr.lardon.bibliointerfaceutilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.lardon.bibliointerfaceutilisateur")
/**
 * classe permettant le démarrage du microservice gestion interface utilisateur
 */
public class
BiblioInterfaceUtilisateurApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BiblioInterfaceUtilisateurApplication.class, args);
	}

}
