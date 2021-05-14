package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie getByLibelle(String libelle);
}
