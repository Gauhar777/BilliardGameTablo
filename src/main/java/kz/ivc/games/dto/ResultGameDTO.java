package kz.ivc.games.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResultGameDTO implements Serializable {
    private long idGamer;
    private long idCompetition;
    private long id;
    private String nick;
    private long point1;
    private long point2;

}
