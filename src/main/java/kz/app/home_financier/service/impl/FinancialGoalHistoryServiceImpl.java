package kz.app.home_financier.service.impl;

import kz.app.home_financier.model.entity.FinancialGoal;
import kz.app.home_financier.model.entity.FinancialGoalHistory;
import kz.app.home_financier.repository.FinancialGoalHistoryRepository;
import kz.app.home_financier.service.FinancialGoalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialGoalHistoryServiceImpl implements FinancialGoalHistoryService {

    private final FinancialGoalHistoryRepository financialGoalHistoryRepository;

    @Override
    public FinancialGoalHistory save(FinancialGoalHistory financialGoalHistory) {
        return financialGoalHistoryRepository.save(financialGoalHistory);
    }

    @Override
    public List<FinancialGoalHistory> findHistoryByFinancialGoal(FinancialGoal financialGoal) {
        return financialGoalHistoryRepository.findAllByFinancialGoal(financialGoal);
    }
}
