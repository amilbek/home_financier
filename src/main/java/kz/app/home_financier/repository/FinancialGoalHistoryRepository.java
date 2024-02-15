package kz.app.home_financier.repository;

import kz.app.home_financier.model.entity.FinancialGoal;
import kz.app.home_financier.model.entity.FinancialGoalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialGoalHistoryRepository extends JpaRepository<FinancialGoalHistory, Long> {

    List<FinancialGoalHistory> findAllByFinancialGoal(FinancialGoal financialGoal);
}
