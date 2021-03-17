package fr.BanqueImageJava.controllers;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.services.CategorieService;
import fr.BanqueImageJava.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/images")
public class ImageController {
    private final static Logger log = LoggerFactory.getLogger(ImageController.class);

    public ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public List<Image> getAllImages() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Image getOneImage(@PathVariable("id") Long id) {
        return service.read(id);
    }


}
