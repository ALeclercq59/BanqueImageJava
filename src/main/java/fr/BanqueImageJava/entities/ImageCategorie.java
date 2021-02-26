package fr.BanqueImageJava.entities;

import javax.persistence.*;

@Entity
@Table(name = "image_categorie")
public class ImageCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimagecategorie")
    private Long id;


    private Long idCategorie;
    private String idImage;


    @Override
    public String toString() {
        return "ImageCategorie{" +
                "id=" + id +
                ", idCategorie=" + idCategorie +
                ", idImage='" + idImage + '\'' +
                '}';
    }
}
