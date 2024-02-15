package kz.app.home_financier.service.impl;

import kz.app.home_financier.model.entity.FinancialGoal;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.repository.FinancialGoalRepository;
import kz.app.home_financier.service.FinancialGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialGoalServiceImpl implements FinancialGoalService {

    private final FinancialGoalRepository financialGoalRepository;

    @Override
    public FinancialGoal save(FinancialGoal financialGoal) {
        return financialGoalRepository.save(financialGoal);
    }

    @Override
    public FinancialGoal findFinancialGoalById(Long id) {
        return financialGoalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Financial goal history not found with id " + id));
    }


    @Override
    public List<FinancialGoal> findAllByUser(User user) {
        return financialGoalRepository.findAllByUser(user);
    }
}
