package fr.lardon.bibliobatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
/**
 * classe permettant le démarrage du microservice batch
 */
public class BiblioBatchApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BiblioBatchApplication.class, args);
	}

}
