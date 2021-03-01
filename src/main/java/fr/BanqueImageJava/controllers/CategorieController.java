package fr.BanqueImageJava.controllers;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.services.CategorieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategorieController {
    private final static Logger log = LoggerFactory.getLogger(CategorieController.class);

    private CategorieService service;

    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public List<Categorie> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Categorie getOneCategory(@PathVariable("id") Long id) {
        return service.read(id);
    }
}
