package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.Livre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoLivre extends JpaRepository<Livre, Integer> {

}
