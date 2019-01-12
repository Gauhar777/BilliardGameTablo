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

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public long getDeference() {
        return deference;
    }

    public void setDeference(long deference) {
        this.deference = deference;
    }

    public int getAgrBall() {
        return agrBall;
    }

    public void setAgrBall(int agrBall) {
        this.agrBall = agrBall;
    }
}
