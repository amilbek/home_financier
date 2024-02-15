package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialGoalCreateDTO {

    private BigDecimal sum;
    private String goal;
}
