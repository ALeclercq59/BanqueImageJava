package fr.BanqueImageJava.services;

import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.repositories.ImageRepository;
import fr.BanqueImageJava.services.client.FileNameDTO;
import fr.BanqueImageJava.services.client.SignUrlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void urlSignImage(String nameImage, String path) throws IOException {

        WebClient client = WebClient.builder().baseUrl("https://uf8m8gb2k0.execute-api.eu-central-1.amazonaws.com").defaultHeader("x-api-key","meRxq0l2cE5SQfNNrfnKUalbs0VIRkIA5KFI8hSF").build();
        var filename = new FileNameDTO();
        filename.setFilename(nameImage);

        var result = client.post().uri("/epsi/generate-signed-url").bodyValue(filename).retrieve().bodyToMono(SignUrlResponse.class);
        var response = result.block();

        var signedUrl = response.getSignedUrl();
        var fileName = response.getFilename();

        System.out.println(response.getSignedUrl());

    }
}
