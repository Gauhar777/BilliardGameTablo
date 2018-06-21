package kz.ivc.games.dto;

import kz.ivc.games.entity.Game;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultDTO implements Serializable {
    private long id;
    private String nick;
    private List<ResultGameDTO> gameList;

    private int agrBall;
    private long agrPoint1;
    private long agrPoint2;
    private long deference;

    public long getDeference() {
        return deference;
    }
}
