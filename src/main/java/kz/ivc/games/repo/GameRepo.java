package kz.ivc.games.repo;

import kz.ivc.games.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game,Long> {
    Game findByIdCompetition(long idCompetition);
    Game findByIdPartner1AndIdPartner2AndIdCompetition(long IdPartner1,long IdPartner2,long IdCompetition);
    List<Game> findByIdPartner1AndIdCompetition(long IdPartner1,long IdCompetition);
    List<Game> findByIdPartner2AndIdCompetition(long IdPartner2,long IdCompetition);
    List<Game> findByIdPartner1(Long idPartner1);
    List<Game> findByIdPartner2(Long idPartner1);
    List<Game> findByIdCompetition(Long idCompetition);
}
