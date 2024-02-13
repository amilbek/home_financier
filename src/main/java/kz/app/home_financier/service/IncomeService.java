package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.Income;
import org.springframework.stereotype.Service;

@Service
public interface IncomeService {

    Income save(Income income);
}
