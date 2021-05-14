package fr.BanqueImageJava.services.categorie;

import fr.BanqueImageJava.entities.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> getAll();
    Categorie create(Categorie categorie);
    Categorie read(Long id);
    Categorie update(Categorie categorie);
    void delete(Long id);
    Categorie getOneCategoryByLibelle(String libelle);
}
