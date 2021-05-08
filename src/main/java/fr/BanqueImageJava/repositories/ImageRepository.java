package fr.BanqueImageJava.repositories;

import fr.BanqueImageJava.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> getByPublicationEquals(int publication);
}
