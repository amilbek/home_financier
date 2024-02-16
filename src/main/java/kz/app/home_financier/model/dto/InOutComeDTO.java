package kz.app.home_financier.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InOutComeDTO {

    private Long id;
    private BigDecimal sum;
    private CategoryDTO category;
    private String comment;
    private LocalDateTime createdAt;
}
