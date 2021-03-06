package fr.BanqueImageJava.services.image;

import fr.BanqueImageJava.entities.Categorie;
import fr.BanqueImageJava.entities.Image;
import fr.BanqueImageJava.entities.MotCle;
import fr.BanqueImageJava.repositories.ImageRepository;
import fr.BanqueImageJava.services.categorie.CategorieService;
import fr.BanqueImageJava.services.client.DetectLbalResponse;
import fr.BanqueImageJava.services.client.FileNameDTO;
import fr.BanqueImageJava.services.client.SignUrlResponse;
import fr.BanqueImageJava.services.motCle.MotCleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final static Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository repository;

    private final CategorieService categorieService;

    private final MotCleService motCleService;

    public ImageServiceImpl(ImageRepository repository, CategorieService categorieService, MotCleService motCleService) {
        log.trace("ImageServiceImpl instanced ");
        this.repository = repository;
        this.categorieService = categorieService;
        this.motCleService = motCleService;
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

    public DetectLbalResponse urlSignImage(String nameImage) throws Exception {

        WebClient client = WebClient.builder().baseUrl("https://uf8m8gb2k0.execute-api.eu-central-1.amazonaws.com").defaultHeader("x-api-key","meRxq0l2cE5SQfNNrfnKUalbs0VIRkIA5KFI8hSF").build();
        var filename = new FileNameDTO();
        filename.setFilename(nameImage);

        var result = client.post().uri("/epsi/generate-signed-url").bodyValue(filename).retrieve().bodyToMono(SignUrlResponse.class);
        var response = result.block();
        var signedUrl = response.getSignedUrl();
        var fileName = response.getFilename();

        System.out.println(fileName);

        System.out.println(nameImage);

        File file = new File("D:\\Cours B3\\JAVA\\TP-BanqueImage\\TP-BanqueImage\\src\\main\\resources\\images\\" + nameImage);

        byte[] data = Files.readAllBytes(file.toPath());

        var urlFactory = new DefaultUriBuilderFactory(signedUrl);
        urlFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        var uploadResponse = WebClient.builder()
                .uriBuilderFactory(urlFactory)
                .build()
                .put()
                .contentType(MediaType.parseMediaType("image/png"))
                .bodyValue(data)
                .retrieve()
                .toBodilessEntity()
                .block();

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/epsi/detect-labels/{url}").build(fileName))
                .retrieve()
                .bodyToMono(DetectLbalResponse.class)
                .block();
    }

    public void addCategorieForImage(Long id, String[] categories) {
        Image image = read(id);
        var listCategories = image.getCategories();

        for (String nameCat : categories) {
            if (!image.getCategories().contains(categorieService.getOneCategoryByLibelle(nameCat))) {
                listCategories.add(categorieService.getOneCategoryByLibelle(nameCat));
            }
        }

        image.setCategories(listCategories);

        update(image);
    }

    public void addMotclesForImage(Long id, String[] motcles) {
        Image image = read(id);
        var listMotcles = image.getMotCles();

        for (String nameMotcle : motcles) {
            if(motCleService.getOneMotCleByLibelle(nameMotcle) != null) {
                listMotcles.add(motCleService.getOneMotCleByLibelle(nameMotcle));
            }
            else {
                MotCle motCle = new MotCle();
                motCle.setLibelle(nameMotcle);
                var motCleCreated = motCleService.create(motCle);
                listMotcles.add(motCleCreated);
            }
        }

        image.setMotCles(listMotcles);

        update(image);
    }

    public List<Image> getImageByPublication(Long id) {
        return repository.getByPublicationEquals(Math.toIntExact(id));
    }

}
