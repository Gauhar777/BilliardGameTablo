package kz.ivc.games.repo;

import kz.ivc.games.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gauha on 1/5/2019.
 */
@Repository
public interface PhotoRepo extends JpaRepository<Photo,Long> {
//    Photo findByIdCompetition(Long idCompetition);
    List<Photo> findByIdCompetition(Long idCompetition);
}
