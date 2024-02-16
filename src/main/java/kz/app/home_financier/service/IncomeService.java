package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.Income;
import kz.app.home_financier.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncomeService {

    Income save(Income income);

    List<Income> findAllIncomesByUser(User user);

    Income findById(Long id);
}
