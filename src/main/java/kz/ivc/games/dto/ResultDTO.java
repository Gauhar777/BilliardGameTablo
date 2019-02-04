package kz.ivc.games.dto;

import kz.ivc.games.entity.Game;
import kz.ivc.games.entity.Gamer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultDTO implements Serializable {
    private long id;
    private String nick;
    private List<ResultGameDTO> gameList;
    private  String dezhuril;
    private  String nick2;
    private int agrBall;
    private long agrPoint1;
    private long agrPoint2;
    private long deference;
    private long idPartner1;

    public long getIdPartner1() {
        return idPartner1;
    }

    public void setIdPartner1(long idGamer1) {
        this.idPartner1= idPartner1;
    }

    public int getAgrBall() {
        return agrBall;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public List<ResultGameDTO> getGameList() {
        return gameList;
    }

    public void setGameList(List<ResultGameDTO> gameList) {
        this.gameList = gameList;
    }

    public void setAgrBall(int agrBall) {
        this.agrBall = agrBall;
    }

    public long getAgrPoint1() {
        return agrPoint1;
    }

    public void setAgrPoint1(long agrPoint1) {
        this.agrPoint1 = agrPoint1;
    }

    public long getAgrPoint2() {
        return agrPoint2;
    }

    public void setAgrPoint2(long agrPoint2) {
        this.agrPoint2 = agrPoint2;
    }

    public long getDeference() {
        return deference;
    }

    public void setDeference(long deference) {
        this.deference = deference;
    }

    public String getDezhuril() {
        return dezhuril;
    }

    public void setDezhuril(String dezhuril) {
        this.dezhuril = dezhuril;
    }

    public String getNick2() {
        return nick2;
    }

    public void setNick2(String nick2) {
        this.nick2 = nick2;
    }
}
