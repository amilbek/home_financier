package kz.app.home_financier.service.impl;

import kz.app.home_financier.model.entity.Outcome;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.repository.OutcomeRepository;
import kz.app.home_financier.service.OutcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutcomeServiceImpl implements OutcomeService {

    private final OutcomeRepository outcomeRepository;

    @Override
    public Outcome save(Outcome outcome) {
        return outcomeRepository.save(outcome);
    }

    @Override
    public List<Outcome> findAllIncomesByUser(User user) {
        return outcomeRepository.findAllByUser(user);
    }
}
