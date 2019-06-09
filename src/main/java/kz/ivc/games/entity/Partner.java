package kz.ivc.games.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name="partner")
public class Partner implements Serializable {
    public Partner(){}


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gamer")
    private Long idGamer;

    @Column(name="id_competition")
    private Long idCompetition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGamer() {
        return idGamer;
    }

    public void setIdGamer(Long idGamer) {
        this.idGamer = idGamer;
    }

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }
}
