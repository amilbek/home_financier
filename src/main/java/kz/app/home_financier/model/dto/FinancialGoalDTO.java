package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FinancialGoalDTO {

    private Long id;
    private String goal;
    private BigDecimal sum;
    private Boolean isAchieved;
    private List<FinancialGoalHistoryDTO> financialGoalHistoryDTOList;
}
