package kz.app.home_financier.repository;

import kz.app.home_financier.model.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
}
