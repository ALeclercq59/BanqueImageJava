package fr.BanqueImageJava.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long id;

    private String name;
    private String firstname;
    private String email;
    private String password;
    private Long role;

    @OneToMany(mappedBy = "user")
    private List<Image> images;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
