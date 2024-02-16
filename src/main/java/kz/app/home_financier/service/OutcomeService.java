package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.Outcome;
import kz.app.home_financier.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OutcomeService {

    Outcome save(Outcome outcome);

    List<Outcome> findAllIncomesByUser(User user);

    Outcome findById(Long id);
}
