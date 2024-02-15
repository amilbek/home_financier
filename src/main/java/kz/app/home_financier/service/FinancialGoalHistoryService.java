package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.FinancialGoal;
import kz.app.home_financier.model.entity.FinancialGoalHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FinancialGoalHistoryService {

    FinancialGoalHistory save(FinancialGoalHistory financialGoalHistory);

    List<FinancialGoalHistory> findHistoryByFinancialGoal(FinancialGoal financialGoal);
}
