package fr.BanqueImageJava.services.motCle;

import fr.BanqueImageJava.entities.MotCle;
import fr.BanqueImageJava.repositories.MotCleRepository;
import fr.BanqueImageJava.services.image.ImageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotCleServiceImpl implements MotCleService {
    private final static Logger log = LoggerFactory.getLogger(MotCleServiceImpl.class);

    private final MotCleRepository repository;

    public MotCleServiceImpl(MotCleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MotCle> getAll() {
        return repository.findAll();
    }

    @Override
    public MotCle create(MotCle motCle) {
        return repository.save(motCle);
    }

    @Override
    public MotCle read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MotCle update(MotCle motCle) {
        return repository.saveAndFlush(motCle);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public MotCle getOneMotCleByLibelle(String libelle) {
        return repository.getByLibelle(libelle);
    }
}
