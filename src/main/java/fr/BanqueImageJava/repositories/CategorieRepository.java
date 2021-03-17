package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie getByLibelle(String libelle);
}
