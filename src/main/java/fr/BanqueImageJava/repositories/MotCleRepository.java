package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.MotCle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotCleRepository extends JpaRepository<MotCle, Long> {
    MotCle getByLibelle(String Libelle);
}
