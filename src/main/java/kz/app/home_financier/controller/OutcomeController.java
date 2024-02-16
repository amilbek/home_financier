package kz.app.home_financier.controller;

import kz.app.home_financier.facade.OutcomeFacade;
import kz.app.home_financier.model.dto.InOutComeCreateDTO;
import kz.app.home_financier.model.dto.InOutComeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/outcomes")
@RequiredArgsConstructor
public class OutcomeController {

    private final OutcomeFacade outcomeFacade;

    @PostMapping("/save-outcome")
    public ResponseEntity<InOutComeDTO> addOutcomeCategory(@RequestBody InOutComeCreateDTO inOutComeCreateDTO) {
        return ResponseEntity.ok(outcomeFacade.saveOutcome(inOutComeCreateDTO));
    }

    @PutMapping("/edit-income/{id}")
    public ResponseEntity<InOutComeDTO> updateOutcome(@PathVariable Long id, @RequestBody InOutComeCreateDTO inOutComeCreateDTO) {
        return ResponseEntity.ok(outcomeFacade.updateIncome(id, inOutComeCreateDTO));
    }

    @DeleteMapping("/delete-income/{id}")
    public ResponseEntity<Void> deleteOutcome(@PathVariable Long id) {
        outcomeFacade.deleteIncome(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/list")
    public ResponseEntity<List<InOutComeDTO>> getOutcomes() {
        return ResponseEntity.ok(outcomeFacade.getAllOutcomesByUser());
    }
}
