package kz.ivc.games.dto;

import lombok.Data;

/**
 * Created by gauha on 1/30/2019.
 */
@Data
public class PointForm {
    private Long idCompetition;
    private Long idPartner1;
    private Long idPartner2;
    private Long point1;
    private Long point2;

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }

    public Long getIdPartner1() {
        return idPartner1;
    }

    public void setIdPartner1(Long idPartner1) {
        this.idPartner1 = idPartner1;
    }

    public Long getIdPartner2() {
        return idPartner2;
    }

    public void setIdPartner2(Long idPartner2) {
        this.idPartner2 = idPartner2;
    }

    public Long getPoint1() {
        return point1;
    }

    public void setPoint1(Long point1) {
        point1 = point1;
    }

    public Long getPoint2() {
        return point2;
    }

    public void setPoint2(Long point2) {
        point2 = point2;
    }
}
