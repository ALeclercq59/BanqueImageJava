package fr.BanqueImageJava.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.swing.text.Element;
import java.time.LocalDateTime;
import java.util.Date;
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

    private String description;
    private String lien;
    private int copyright;
    private int publication;

    @Column(name = "dateaccordpersonnepresente")
    private LocalDateTime dateAccord;

    @ManyToMany
    @JoinTable(name = "image_categorie",
            joinColumns = @JoinColumn(name = "idimage"),
            inverseJoinColumns = @JoinColumn(name = "idcategorie"))
    @ToString.Exclude
    @JsonIgnoreProperties("images")
    private List<Categorie> categories;

    @ManyToOne
    @JoinColumn(name = "iduser")
    @JsonIgnoreProperties("images")
    private Users users;
}
