package fr.BanqueImageJava.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimage")
    private Long id;

    private String description;
    private String lien;
    private int copyright;
    private int publication;

    @Column(name = "dateaccordpersonnepresente")
    private Date dateAccord;

    @ManyToMany
    @JoinTable(name = "image_categorie",
            joinColumns = @JoinColumn(name = "idImage"),
            inverseJoinColumns = @JoinColumn(name = "idCategorie"))
    private List<Categorie> categories;

    @ManyToOne()
    @JoinColumn(name = "iduser")
    private User user;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", lien='" + lien + '\'' +
                ", copyright=" + copyright +
                ", publication=" + publication +
                ", dateAccordPersonnePresente=" + dateAccord +
                ", user=" + user +
                '}';
    }
}
