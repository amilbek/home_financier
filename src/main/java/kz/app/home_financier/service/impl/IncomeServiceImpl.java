package kz.app.home_financier.service.impl;

import kz.app.home_financier.model.entity.Income;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.repository.IncomeRepository;
import kz.app.home_financier.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Override
    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> findAllIncomesByUser(User user) {
        return incomeRepository.findAllByUser(user);
    }

    @Override
    public Income findById(Long id) {
        return incomeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("income not found with id " + id));
    }
}
