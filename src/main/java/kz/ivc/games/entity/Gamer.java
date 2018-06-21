package kz.ivc.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by balgi on 05.06.2018.
 */

@Data
@Entity(name = "gamer")
public class Gamer implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    private String FIO;

    public String getFIO() {
        return FIO;
    }
    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    @Column
    private String nick;

    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }


}
