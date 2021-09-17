package fr.lardon.biblioauthentificationutilisateur.dao;

import fr.lardon.biblioauthentificationutilisateur.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoRole extends JpaRepository<Role, Integer> {

    Role findByCode(int code);

}
