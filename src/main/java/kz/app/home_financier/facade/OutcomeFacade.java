package kz.app.home_financier.facade;

import kz.app.home_financier.model.dto.InOutComeCreateDTO;
import kz.app.home_financier.model.dto.InOutComeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OutcomeFacade {

    InOutComeDTO saveOutcome(InOutComeCreateDTO inOutComeCreateDTO);

    InOutComeDTO updateIncome(Long id, InOutComeCreateDTO inOutComeCreateDTO);

    void deleteIncome(Long id);

    List<InOutComeDTO> getAllOutcomesByUser();
}
