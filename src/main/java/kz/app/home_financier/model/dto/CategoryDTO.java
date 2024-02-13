package kz.app.home_financier.model.dto;

import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;
    private String name;
    private Boolean customCategory;
    private Boolean isIncome;
    private Boolean isOutcome;
}
