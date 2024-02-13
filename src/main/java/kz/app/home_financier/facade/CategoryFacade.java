package kz.app.home_financier.facade;

import kz.app.home_financier.model.dto.CategoryCreateDTO;
import kz.app.home_financier.model.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryFacade {

    CategoryDTO addIncomeCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryDTO addOutcomeCategory(CategoryCreateDTO categoryCreateDTO);

    List<CategoryDTO> getUserIncomeCategories();

    List<CategoryDTO> getUserOutcomeCategories();
}
