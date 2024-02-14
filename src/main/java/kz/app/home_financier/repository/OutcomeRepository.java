package kz.app.home_financier.repository;

import kz.app.home_financier.model.entity.Outcome;
import kz.app.home_financier.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

    List<Outcome> findAllByUser(User user);
}
