package kz.ivc.games.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "dezhurny")
@Data
public class Dezhurny implements Serializable {
    public Dezhurny(){ }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gamer")
    private Long idGamer;

//    @Column(name = "time")
//    private Long time;

    @Column(name="id_competition")
    private Long idCompetition;

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }

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

//    public Long getTime() {
//        return time;
//    }
//
//    public void setTime(Long time) {
//        this.time = time;
//    }

    public Long getIdCompetition() {
        return idCompetition;
    }
}


