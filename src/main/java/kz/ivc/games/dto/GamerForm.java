package kz.ivc.games.dto;

import lombok.Data;

/**
 * Created by balgi on 05.06.2018.
 */

public class GamerForm {

    private String id;
    private String nick;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
