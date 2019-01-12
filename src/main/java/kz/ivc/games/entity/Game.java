package kz.ivc.games.entity;

import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;

@Data
@Entity(name="game")
public class Game {
    public Game(){}
    public Game(Long id,Long id_partner1,Long id_partner2,Long id_competition,Long point1,Long point2){

    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_partner1")
    private Long idPartner1;

    @Column(name = "id_partner2")
    private Long idPartner2;

    @Column(name = "id_competition")
    private Long idCompetition;

    @Column(name = "point1")
    private Long point1;

    @Column(name = "point2")
    private Long point2;


    public Long getIdPartner2() {
        return idPartner2;
    }

    public void setIdPartner2(Long idPartner2) {
        this.idPartner2 = idPartner2;
    }

    public Long getPoint1() {
        return point1;
    }

    public Long getPoint2() {
        return point2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPartner1() {
        return idPartner1;
    }

    public void setIdPartner1(Long idPartner1) {
        this.idPartner1 = idPartner1;
    }

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }

    public void setPoint1(Long point1) {
        this.point1 = point1;
    }

    public void setPoint2(Long point2) {
        this.point2 = point2;
    }
}
