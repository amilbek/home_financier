package kz.app.home_financier.controller;

import kz.app.home_financier.facade.IncomeFacade;
import kz.app.home_financier.model.dto.InOutComeCreateDTO;
import kz.app.home_financier.model.dto.InOutComeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/list")
    public ResponseEntity<List<InOutComeDTO>> getIncomes() {
        return ResponseEntity.ok(incomeFacade.getAllIncomesByUser());
    }
}
