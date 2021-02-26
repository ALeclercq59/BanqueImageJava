package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.ImageCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageCategorieRepository extends JpaRepository<ImageCategorie, Long> {

}
