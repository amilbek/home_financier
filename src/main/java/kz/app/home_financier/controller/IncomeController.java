package kz.app.home_financier.controller;

import kz.app.home_financier.facade.IncomeFacade;
import kz.app.home_financier.model.dto.InOutComeCreateDTO;
import kz.app.home_financier.model.dto.InOutComeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeFacade incomeFacade;

    @PostMapping("/save-income")
    public ResponseEntity<InOutComeDTO> addIncomeCategory(@RequestBody InOutComeCreateDTO inOutComeCreateDTO) {
        return ResponseEntity.ok(incomeFacade.saveIncome(inOutComeCreateDTO));
    }

    @PutMapping("/edit-income/{id}")
    public ResponseEntity<InOutComeDTO> updateIncome(@PathVariable Long id, @RequestBody InOutComeCreateDTO inOutComeCreateDTO) {
        return ResponseEntity.ok(incomeFacade.updateIncome(id, inOutComeCreateDTO));
    }

    @DeleteMapping("/delete-income/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeFacade.deleteIncome(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/list")
    public ResponseEntity<List<InOutComeDTO>> getIncomes() {
        return ResponseEntity.ok(incomeFacade.getAllIncomesByUser());
    }
}
