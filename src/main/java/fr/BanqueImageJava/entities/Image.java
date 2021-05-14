package fr.BanqueImageJava.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "image")
@Setter
@Getter
@ToString
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimage")
    private Long id;
    private String title;
    private String name;
    private String description;
    private String lien;
    private Long copyright;
    private Long publication;

    @Column(name = "dateaccordpersonnepresente")
    private LocalDate dateAccord;

    @ManyToMany
    @JoinTable(name = "image_categorie",
            joinColumns = @JoinColumn(name = "idimage"),
            inverseJoinColumns = @JoinColumn(name = "idcategorie"))
    @ToString.Exclude
    @JsonIgnoreProperties("images")
    private List<Categorie> categories;

    @ManyToMany
    @JoinTable(name = "image_mot_cle",
            joinColumns = @JoinColumn(name = "idimage"),
            inverseJoinColumns = @JoinColumn(name = "idmotcle"))
    @ToString.Exclude
    @JsonIgnoreProperties("images")
    private List<MotCle> motCles;

    @ManyToOne
    @JoinColumn(name = "iduser")
    @JsonIgnoreProperties("images")
    private Users users;
}
