package kz.ivc.games.repo;

import kz.ivc.games.entity.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitationRepo extends JpaRepository<Competition,Long> {

    public List<Competition> findAllByOrderByIdDesc();
    Competition findIdByName(String name);
}
