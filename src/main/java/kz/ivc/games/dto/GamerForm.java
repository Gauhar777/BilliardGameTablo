package kz.ivc.games.dto;

/**
 * Created by balgi on 05.06.2018.
 */
public class GamerForm {

    private String Id;
    private String FIO;
    private String nick;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
