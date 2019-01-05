package kz.ivc.games.repo;

import kz.ivc.games.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gauha on 1/5/2019.
 */
@Repository
public interface PhotoRepo extends JpaRepository<Photo,Long> {

}
