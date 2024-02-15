package kz.app.home_financier.facade;

import kz.app.home_financier.model.dto.FinancialGoalCreateDTO;
import kz.app.home_financier.model.dto.FinancialGoalDTO;
import kz.app.home_financier.model.dto.FinancialGoalHistoryCreateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FinancialGoalFacade {

    FinancialGoalDTO saveFinancialGoal(FinancialGoalCreateDTO financialGoalCreateDTO);

    void deleteFinancialGoal(Long id);

    List<FinancialGoalDTO> getFinancialGoalsByUser();

    FinancialGoalDTO saveFinancialGoalHistory(FinancialGoalHistoryCreateDTO financialGoalHistoryCreateDTO);

    FinancialGoalDTO getFinancialGoalById(Long financialGoalId);
}
