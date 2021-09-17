package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.AbonnePret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonnePret extends JpaRepository<AbonnePret, Integer> {

}
