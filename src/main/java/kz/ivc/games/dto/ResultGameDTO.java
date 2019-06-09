package kz.ivc.games.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResultGameDTO implements Serializable {
    private long idGamer;
    private long idCompetition;
    private long id;
    private String nickOfPartner2;
    //private String nickPartner2;
    private long point1;
    private long point2;
    private long idPartner2;


    public String getNickOfPartner2() {
        return nickOfPartner2;
    }

    public void setNickOfPartner2(String nickOfPartner2) {
        this.nickOfPartner2 = nickOfPartner2;
    }

    public long getIdPartner2() {
        return idPartner2;
    }

    public void setIdPartner2(long idPartner2) {
        this.idPartner2 = idPartner2;
    }

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
