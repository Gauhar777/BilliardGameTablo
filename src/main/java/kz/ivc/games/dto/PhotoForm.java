package kz.ivc.games.dto;

import lombok.Data;

/**
 * Created by gauha on 1/5/2019.
 */
@Data
public class PhotoForm {
    private String id;
    private String name;
    private String idCompetition;
    public PhotoForm(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(String idCompetition) {
        this.idCompetition = idCompetition;
    }
}
