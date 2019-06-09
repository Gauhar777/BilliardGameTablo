package kz.ivc.games.dto;

/**
 * Created by balgi on 15.06.2018.
 */
public class GameForm {

    private String Id;
    private String id_partner1;
    private String id_partner2;
    private String point1;
    private String point2;
    private String id_competition;




    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getId_partner1() {
        return id_partner1;
    }

    public void setId_partner1(String id_partner1) {
        this.id_partner1 = id_partner1;
    }

    public String getId_partner2() {
        return id_partner2;
    }

    public void setId_partner2(String id_partner2) {
        this.id_partner2 = id_partner2;
    }

    public String getPoint1() {
        return point1;
    }

    public void setPoint1(String point1) {
        this.point1 = point1;
    }

    public String getPoint2() {
        return point2;
    }

    public void setPoint2(String point2) {
        this.point2 = point2;
    }

    public String getId_competition() {
        return id_competition;
    }

    public void setId_competition(String id_competition) {
        this.id_competition = id_competition;
    }



}
