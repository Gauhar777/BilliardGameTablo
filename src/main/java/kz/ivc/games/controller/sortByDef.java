package kz.ivc.games.controller;

import kz.ivc.games.dto.ResultDTO;
import kz.ivc.games.entity.Gamer;

import java.util.Comparator;

public class sortByDef implements Comparator<ResultDTO> {
    public int compare(ResultDTO a,ResultDTO b)
    {
        return (int) (b.getDeference()-a.getDeference());
    }
}
