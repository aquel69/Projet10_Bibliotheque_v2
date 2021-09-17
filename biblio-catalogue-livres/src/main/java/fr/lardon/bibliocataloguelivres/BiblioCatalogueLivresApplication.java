package fr.lardon.bibliocataloguelivres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
/**
 * classe permettant le démarrage du microservice catalogue livres
 */
public class BiblioCatalogueLivresApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BiblioCatalogueLivresApplication.class, args);
	}

}
