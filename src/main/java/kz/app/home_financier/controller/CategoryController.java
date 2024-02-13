package kz.app.home_financier.controller;


import kz.app.home_financier.facade.CategoryFacade;
import kz.app.home_financier.model.dto.CategoryCreateDTO;
import kz.app.home_financier.model.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFacade categoryFacade;

    @PostMapping("/add-income")
    public ResponseEntity<CategoryDTO> addIncomeCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseEntity.ok(categoryFacade.addIncomeCategory(categoryCreateDTO));
    }

    @PostMapping("/add-outcome")
    public ResponseEntity<CategoryDTO> addOutcomeCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseEntity.ok(categoryFacade.addOutcomeCategory(categoryCreateDTO));
    }

    @GetMapping("/outcome-categories")
    public ResponseEntity<List<CategoryDTO>> getUserIncomeCategories() {
        return ResponseEntity.ok(categoryFacade.getUserIncomeCategories());
    }

    @GetMapping("/income-categories")
    public ResponseEntity<List<CategoryDTO>> getUserOutcomeCategories() {
        return ResponseEntity.ok(categoryFacade.getUserOutcomeCategories());
    }
}
