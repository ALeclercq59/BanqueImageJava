package fr.BanqueImageJava.services.image;

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
    void addMotclesForImage(Long id, String[] motcles);
    List<Image> getImageByPublication(Long id);
}
