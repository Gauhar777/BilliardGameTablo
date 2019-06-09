package kz.ivc.games.repo;

import kz.ivc.games.entity.Dezhurny;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DezhurnyRepo extends JpaRepository<Dezhurny, Long> {
    Dezhurny findByIdCompetitionAndIdGamer(long idCompetition, long idGamer);

    Dezhurny findByIdCompetition(Long idCompetition);
}
