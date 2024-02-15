package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FinancialGoalHistoryDTO {

    private Long id;
    private BigDecimal sum;
    private LocalDateTime createdAt;
}
