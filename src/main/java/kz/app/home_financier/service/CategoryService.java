package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.Category;
import kz.app.home_financier.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    Category save(Category category);

    Category findCategoryByName(String category);

    List<Category> getIncomeCategoriesByUser(User user);

    List<Category> getOutcomeCategoriesByUser(User user);

    List<Category> getBasicIncomeCategories();

    List<Category> getBasicOutcomeCategories();
}
