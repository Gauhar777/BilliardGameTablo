package kz.ivc.games.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gauha on 1/5/2019.
 */
@Data
@Entity(name = "photo")
public class Photo implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column
    private String name;

    @Column
    private String path;

    @Column
    private String extension;

    @Column
    private Long idCompetition;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }
}
