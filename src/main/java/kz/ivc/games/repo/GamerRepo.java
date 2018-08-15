package kz.ivc.games.repo;


import kz.ivc.games.entity.Game;
import kz.ivc.games.entity.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by balgin on 05.06.2018.
 */


@Repository
public interface GamerRepo extends JpaRepository<Gamer, Long> {

}


