package kz.app.home_financier.controller;

import kz.app.home_financier.facade.FinancialGoalFacade;
import kz.app.home_financier.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/financial-goals")
@RequiredArgsConstructor
public class FinancialGoalController {

    private final FinancialGoalFacade financialGoalFacade;

    @PostMapping("/add")
    public ResponseEntity<FinancialGoalDTO> addFinancialGoal(@RequestBody FinancialGoalCreateDTO financialGoalCreateDTO) {
        return ResponseEntity.ok(financialGoalFacade.saveFinancialGoal(financialGoalCreateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFinancialGoal(@PathVariable("id") Long id) {
        financialGoalFacade.deleteFinancialGoal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FinancialGoalDTO>> getFinancialGoalsByUser() {
        return ResponseEntity.ok(financialGoalFacade.getFinancialGoalsByUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialGoalDTO> getFinancialGoalById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(financialGoalFacade.getFinancialGoalById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialGoalDTO> setAchievedGoal(@PathVariable("id") Long id) {
        return ResponseEntity.ok(financialGoalFacade.setAchievedGoal(id));
    }

    @PostMapping("/add-history")
    public ResponseEntity<FinancialGoalDTO> addIncomeCategory(@RequestBody FinancialGoalHistoryCreateDTO financialGoalHistoryCreateDTO) {
        return ResponseEntity.ok(financialGoalFacade.saveFinancialGoalHistory(financialGoalHistoryCreateDTO));
    }
}
