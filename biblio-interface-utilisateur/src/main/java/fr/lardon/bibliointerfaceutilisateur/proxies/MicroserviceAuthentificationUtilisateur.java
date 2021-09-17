package fr.lardon.bibliointerfaceutilisateur.proxies;


import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "microservice-authentification-utilisateur", url = "localhost:9092")
public interface MicroserviceAuthentificationUtilisateur {

    //login de l'utilisateur
    @PostMapping(value="/Login/{motDePasse}/{email}/")
    Abonne login(@PathVariable(value ="motDePasse") String motDePasse, @PathVariable(value = "email") String email );

}
