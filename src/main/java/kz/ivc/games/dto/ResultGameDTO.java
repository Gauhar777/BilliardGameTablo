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

    public long getIdGamer() {
        return idGamer;
    }

    public void setIdGamer(long idGamer) {
        this.idGamer = idGamer;
    }

    public long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(long idCompetition) {
        this.idCompetition = idCompetition;
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

    public long getPoint1() {
        return point1;
    }

    public void setPoint1(long point1) {
        this.point1 = point1;
    }

    public long getPoint2() {
        return point2;
    }

    public void setPoint2(long point2) {
        this.point2 = point2;
    }
}
