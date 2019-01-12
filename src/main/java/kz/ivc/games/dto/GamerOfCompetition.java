package kz.ivc.games.dto;

import lombok.Data;

@Data
public class GamerOfCompetition {
    private String FIO;
    private String nick;
    private boolean choosed;
    private boolean dezhuril;
    private long idGamer;

    public void setDezhuril(boolean dezhuril) {
        this.dezhuril = dezhuril;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

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

    public boolean isDezhuril() {
        return dezhuril;
    }

    public long getIdGamer() {
        return idGamer;
    }

    public void setIdGamer(long idGamer) {
        this.idGamer = idGamer;
    }
}
