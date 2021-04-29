package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.repositories.CategorieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService{
    private final static Logger log = LoggerFactory.getLogger(CategorieServiceImpl.class);

    private final CategorieRepository repository;

    public CategorieServiceImpl(CategorieRepository repository) {
        log.trace("CategorieServiceImpl instanced ");
        this.repository = repository;
    }

    @Override
    public List<Categorie> getAll() {
        return repository.findAll();
    }

    @Override
    public Categorie create(Categorie categorie) {
        return repository.save(categorie);
    }

    @Override
    public Categorie read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Categorie update(Categorie categorie) {
        return repository.saveAndFlush(categorie);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Categorie getOneCategoryByLibelle(String libelle) {
        return repository.getByLibelle(libelle);
    }
}
