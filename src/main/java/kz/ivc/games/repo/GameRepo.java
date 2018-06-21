package kz.ivc.games.repo;

import kz.ivc.games.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game,Long> {
    List<Game> findByIdCompetition(long idCompetition);
    Game findByIdPartner1AndIdPartner2AndIdCompetition(long IdPartner1,long IdPartner2,long IdCompetition);
}
