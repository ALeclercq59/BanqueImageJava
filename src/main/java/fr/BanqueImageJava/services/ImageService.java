package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.services.client.DetectLbalResponse;

import java.util.List;

public interface ImageService {
    List<Image> getAll();
    Image create(Image image);
    Image read(Long id);
    Image update(Image image);
    void delete(Long id);
    DetectLbalResponse urlSignImage(String nameImage) throws Exception;
    void addCategorieForImage(Long id, String[] categories);
}
