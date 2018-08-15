package kz.ivc.games.dto;

import lombok.Data;

@Data
public class GamerOfCompetition {
    private String FIO;
    private String nick;
    private boolean choosed;
    private boolean dezhuril;
    private long idGamer;
}
