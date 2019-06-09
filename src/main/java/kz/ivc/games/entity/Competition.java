package kz.ivc.games.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity(name = "competition")
@Data
public class Competition {

    public Competition(){}

    public Competition(Long id, String name){
        this.id=id;
        this.name=name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column()
    private long winner_game_id;

    public long getWinner_game_id() {
        return winner_game_id;
    }

    public void setWinner_game_id(long winner_game_id) {
        this.winner_game_id = winner_game_id;
    }

    public Long getId(){
    return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

