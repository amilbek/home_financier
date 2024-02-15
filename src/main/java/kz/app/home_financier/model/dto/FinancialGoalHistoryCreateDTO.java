package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialGoalHistoryCreateDTO {

    private Long financialGoalId;
    private BigDecimal sum;
}
