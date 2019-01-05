package kz.ivc.games.repo;

import kz.ivc.games.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepo extends JpaRepository<Partner,Long> {
    List<Partner> findByIdCompetition(long idCompetition);
    Partner findByIdCompetitionAndIdGamer(long idCompetition,long idGamer);
    List<Partner> findByIdGamer(long idGamer);
}