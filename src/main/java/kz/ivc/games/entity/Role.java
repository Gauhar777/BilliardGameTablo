package kz.ivc.games.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "roles")

    private Set<User> users;
}
