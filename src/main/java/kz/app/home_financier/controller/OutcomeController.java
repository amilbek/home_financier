package kz.app.home_financier.controller;

import kz.app.home_financier.facade.OutcomeFacade;
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
@RequestMapping("/api/v1/outcomes")
@RequiredArgsConstructor
public class OutcomeController {

    private final OutcomeFacade outcomeFacade;

    @PostMapping("/save-outcome")
    public ResponseEntity<InOutComeDTO> addOutcomeCategory(@RequestBody InOutComeCreateDTO inOutComeCreateDTO) {
        return ResponseEntity.ok(outcomeFacade.saveOutcome(inOutComeCreateDTO));
    }

    @PostMapping("/list")
    public ResponseEntity<List<InOutComeDTO>> getOutcomes() {
        return ResponseEntity.ok(outcomeFacade.getAllOutcomesByUser());
    }
}
