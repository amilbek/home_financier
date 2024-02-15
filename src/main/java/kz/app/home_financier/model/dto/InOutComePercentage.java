package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InOutComePercentage {

    private BigDecimal sum;
    private BigDecimal percentage;
    private CategoryDTO categoryDTO;
}
