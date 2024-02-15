package kz.app.home_financier.facade;

import kz.app.home_financier.model.dto.InOutComeAnalytics;
import org.springframework.stereotype.Service;

@Service
public interface AnalyticsFacade {

    InOutComeAnalytics getIncomeAnalytics();
    InOutComeAnalytics getOutcomeAnalytics();
}
