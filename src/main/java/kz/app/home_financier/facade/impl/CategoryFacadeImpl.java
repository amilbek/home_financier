package kz.app.home_financier.facade.impl;

import kz.app.home_financier.facade.CategoryFacade;
import kz.app.home_financier.model.dto.CategoryCreateDTO;
import kz.app.home_financier.model.dto.CategoryDTO;
import kz.app.home_financier.model.entity.Category;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.service.CategoryService;
import kz.app.home_financier.service.UserService;
import kz.app.home_financier.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public CategoryDTO addIncomeCategory(CategoryCreateDTO categoryCreateDTO) {
        User user = userService.getAuth();
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        category.setCustomCategory(true);
        category.setIsIncome(true);
        category.setIsOutcome(false);
        category.setUser(user);
        return ModelMapperUtil.map(categoryService.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO addOutcomeCategory(CategoryCreateDTO categoryCreateDTO) {
        User user = userService.getAuth();
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        category.setCustomCategory(true);
        category.setIsIncome(false);
        category.setIsOutcome(true);
        category.setUser(user);
        return ModelMapperUtil.map(categoryService.save(category), CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getUserIncomeCategories() {
        User user = userService.getAuth();
        List<Category> basicCategories = categoryService.getBasicIncomeCategories();
        List<Category> customCategories = categoryService.getIncomeCategoriesByUser(user);
        List<Category> categories = new ArrayList<>();
        categories.addAll(basicCategories);
        categories.addAll(customCategories);
        return categories
                .stream()
                .map(c -> ModelMapperUtil.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getUserOutcomeCategories() {
        User user = userService.getAuth();
        List<Category> basicCategories = categoryService.getBasicOutcomeCategories();
        List<Category> customCategories = categoryService.getOutcomeCategoriesByUser(user);
        List<Category> categories = new ArrayList<>();
        categories.addAll(basicCategories);
        categories.addAll(customCategories);
        return categories
                .stream()
                .map(c -> ModelMapperUtil.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
    }
}
