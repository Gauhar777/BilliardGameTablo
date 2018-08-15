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

    @Column(name = "time")
    private Long time;

    @Column(name="id_competition")
    private Long idCompetition;
}


