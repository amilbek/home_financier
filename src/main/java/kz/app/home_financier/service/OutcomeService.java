package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.Outcome;
import org.springframework.stereotype.Service;

@Service
public interface OutcomeService {

    Outcome save(Outcome outcome);
}
