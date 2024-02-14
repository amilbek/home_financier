package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InOutComeCreateDTO {

    private Long categoryId;
    private BigDecimal sum;
    private String comment;
}
