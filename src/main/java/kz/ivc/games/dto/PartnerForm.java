package kz.ivc.games.dto;

public class PartnerForm {
    private String id;
    private String id_gamer;
    private String id_competition;

    public PartnerForm(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_gamer() {
        return id_gamer;
    }

    public void setId_gamer(String id_gamer) {
        this.id_gamer = id_gamer;
    }

    public String getId_competition() {
        return id_competition;
    }

    public void setId_competition(String id_competition) {
        this.id_competition = id_competition;
    }
}
