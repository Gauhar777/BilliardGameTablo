package kz.ivc.games.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BestGamersDTO implements Serializable {
    private long id;
    private String nick;
    private String FIO;
    private long deference;
    private int agrBall;
}
