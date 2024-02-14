package kz.app.home_financier.service.impl;

import kz.app.home_financier.model.entity.Category;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.repository.CategoryRepository;
import kz.app.home_financier.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String category) {
        return categoryRepository.findByName(category)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with name " + category));
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    }

    @Override
    public List<Category> getIncomeCategoriesByUser(User user) {
        return categoryRepository.findByUserAndIsIncomeIsTrue(user);
    }

    @Override
    public List<Category> getOutcomeCategoriesByUser(User user) {
        return categoryRepository.findByUserAndIsOutcomeIsTrue(user);
    }

    @Override
    public List<Category> getBasicIncomeCategories() {
        return categoryRepository.findByCustomCategoryIsFalseAndIsIncomeIsTrue();
    }

    @Override
    public List<Category> getBasicOutcomeCategories() {
        return categoryRepository.findByCustomCategoryIsFalseAndIsOutcomeIsTrue();
    }
}
