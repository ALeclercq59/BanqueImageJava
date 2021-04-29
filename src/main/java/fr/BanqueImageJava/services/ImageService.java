package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.entities.Image;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<Image> getAll();
    Image create(Image image);
    Image read(Long id);
    Image update(Image image);
    void delete(Long id);
    void urlSignImage(String nameImage, String path) throws IOException;
}
