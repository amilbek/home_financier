package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InOutComeAnalytics {

    private BigDecimal totalSum;
    private List<InOutComePercentage> inOutComePercentageList;
}
