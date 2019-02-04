package kz.ivc.games.dto;

import lombok.Data;

@Data
public class GamerOfCompetition {
    private String nick;
    private boolean choosed;
    private long idGamer;


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }


    public long getIdGamer() {
        return idGamer;
    }

    public void setIdGamer(long idGamer) {
        this.idGamer = idGamer;
    }
}
