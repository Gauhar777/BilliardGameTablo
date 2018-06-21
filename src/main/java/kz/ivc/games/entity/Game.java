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

    @Column
    private Long point1;

    @Column
    private Long point2;


}
