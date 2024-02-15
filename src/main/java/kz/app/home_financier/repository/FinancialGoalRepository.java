package kz.app.home_financier.repository;

import kz.app.home_financier.model.entity.FinancialGoal;
import kz.app.home_financier.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long> {

    List<FinancialGoal> findAllByUser(User user);
}
