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

