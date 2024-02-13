package kz.app.home_financier.service.impl;

import kz.app.home_financier.model.entity.Income;
import kz.app.home_financier.repository.IncomeRepository;
import kz.app.home_financier.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Override
    public Income save(Income income) {
        return incomeRepository.save(income);
    }
}
