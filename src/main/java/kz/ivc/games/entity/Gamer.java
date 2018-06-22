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


    @Column
    private String FIO;


    @Column
    private String nick;


}
