package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    private final static Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository repository;

    public ImageServiceImpl(ImageRepository repository) {
        log.trace("ImageServiceImpl instanced ");
        this.repository = repository;
    }

    @Override
    public List<Image> getAll() {
        return repository.findAll();
    }

    @Override
    public Image create(Image image) {
        return repository.save(image);
    }

    @Override
    public Image read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Image update(Image image) {
        return repository.saveAndFlush(image);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
