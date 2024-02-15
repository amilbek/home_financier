package kz.app.home_financier.facade.impl;

import kz.app.home_financier.facade.AnalyticsFacade;
import kz.app.home_financier.model.dto.CategoryDTO;
import kz.app.home_financier.model.dto.InOutComeAnalytics;
import kz.app.home_financier.model.dto.InOutComePercentage;
import kz.app.home_financier.model.entity.Income;
import kz.app.home_financier.model.entity.Outcome;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.service.IncomeService;
import kz.app.home_financier.service.OutcomeService;
import kz.app.home_financier.service.UserService;
import kz.app.home_financier.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static kz.app.home_financier.util.Utils.toPercentageOf;

@Service
@RequiredArgsConstructor
public class AnalyticsFacadeImpl implements AnalyticsFacade {

    private final UserService userService;
    private final IncomeService incomeService;
    private final OutcomeService outcomeService;

    @Override
    public InOutComeAnalytics getIncomeAnalytics() {
        User user = userService.getAuth();
        InOutComeAnalytics inOutComeAnalytics = new InOutComeAnalytics();
        List<Income> incomes = incomeService.findAllIncomesByUser(user);
        BigDecimal totalSum = incomes
                .stream()
                .map(Income::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        List<InOutComePercentage> inOutComePercentageList = new ArrayList<>();
        incomes.forEach(i -> {
            InOutComePercentage inOutComePercentage = new InOutComePercentage();
            inOutComePercentage.setSum(i.getSum());
            inOutComePercentage.setCategoryDTO(ModelMapperUtil.map(i.getCategory(), CategoryDTO.class));
            inOutComePercentage.setPercentage(toPercentageOf(i.getSum(), totalSum));
            inOutComePercentageList.add(inOutComePercentage);
        });
        inOutComeAnalytics.setTotalSum(totalSum);
        inOutComeAnalytics.setInOutComePercentageList(inOutComePercentageList);
        return inOutComeAnalytics;
    }


    @Override
    public InOutComeAnalytics getOutcomeAnalytics() {
        User user = userService.getAuth();
        InOutComeAnalytics inOutComeAnalytics = new InOutComeAnalytics();
        List<Outcome> outcomes = outcomeService.findAllIncomesByUser(user);
        BigDecimal totalSum = outcomes
                .stream()
                .map(Outcome::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        List<InOutComePercentage> inOutComePercentageList = new ArrayList<>();
        outcomes.forEach(i -> {
            InOutComePercentage inOutComePercentage = new InOutComePercentage();
            inOutComePercentage.setSum(i.getSum());
            inOutComePercentage.setCategoryDTO(ModelMapperUtil.map(i.getCategory(), CategoryDTO.class));
            inOutComePercentage.setPercentage(toPercentageOf(i.getSum(), totalSum));
            inOutComePercentageList.add(inOutComePercentage);
        });
        inOutComeAnalytics.setTotalSum(totalSum);
        inOutComeAnalytics.setInOutComePercentageList(inOutComePercentageList);
        return inOutComeAnalytics;
    }
}
