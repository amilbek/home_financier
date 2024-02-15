package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.FinancialGoal;
import kz.app.home_financier.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FinancialGoalService {

    FinancialGoal save(FinancialGoal financialGoal);

    FinancialGoal findFinancialGoalById(Long id);

    List<FinancialGoal> findAllByUser(User user);
}
