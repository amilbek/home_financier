package kz.app.home_financier.controller;

import kz.app.home_financier.facade.AnalyticsFacade;
import kz.app.home_financier.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsFacade analyticsFacade;

    @PostMapping("/incomes")
    public ResponseEntity<InOutComeAnalytics> incomeAnalytics() {
        return ResponseEntity.ok(analyticsFacade.getIncomeAnalytics());
    }

    @PostMapping("/outcomes")
    public ResponseEntity<InOutComeAnalytics> outcomeAnalytics() {
        return ResponseEntity.ok(analyticsFacade.getOutcomeAnalytics());
    }
}
