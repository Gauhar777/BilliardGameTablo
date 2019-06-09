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
    private int gameCount;
    private String description;
    private boolean avatar;
    private int win;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public boolean isAvatar() {
        return avatar;
    }

    public void setAvatar(boolean avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

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
