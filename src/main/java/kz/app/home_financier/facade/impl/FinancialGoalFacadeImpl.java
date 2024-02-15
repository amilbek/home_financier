package kz.app.home_financier.facade.impl;

import kz.app.home_financier.facade.FinancialGoalFacade;
import kz.app.home_financier.model.dto.FinancialGoalCreateDTO;
import kz.app.home_financier.model.dto.FinancialGoalDTO;
import kz.app.home_financier.model.dto.FinancialGoalHistoryCreateDTO;
import kz.app.home_financier.model.dto.FinancialGoalHistoryDTO;
import kz.app.home_financier.model.entity.FinancialGoal;
import kz.app.home_financier.model.entity.FinancialGoalHistory;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.service.FinancialGoalHistoryService;
import kz.app.home_financier.service.FinancialGoalService;
import kz.app.home_financier.service.UserService;
import kz.app.home_financier.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialGoalFacadeImpl implements FinancialGoalFacade {

    private final UserService userService;
    private final FinancialGoalService financialGoalService;
    private final FinancialGoalHistoryService financialGoalHistoryService;

    @Override
    public FinancialGoalDTO saveFinancialGoal(FinancialGoalCreateDTO financialGoalCreateDTO) {
        User user = userService.getAuth();
        FinancialGoal financialGoal = new FinancialGoal();
        financialGoal.setGoal(financialGoalCreateDTO.getGoal());
        financialGoal.setSum(financialGoalCreateDTO.getSum());
        financialGoal.setUser(user);
        financialGoal.setIsAchieved(false);
        FinancialGoal dbFinancialGoal = financialGoalService.save(financialGoal);
        return ModelMapperUtil.map(dbFinancialGoal, FinancialGoalDTO.class);
    }

    @Override
    public void deleteFinancialGoal(Long id) {
        FinancialGoal financialGoal = financialGoalService.findFinancialGoalById(id);
        financialGoal.setDeletedAt(LocalDateTime.now());
        financialGoalService.save(financialGoal);
    }

    @Override
    public List<FinancialGoalDTO> getFinancialGoalsByUser() {
        User user = userService.getAuth();
        return financialGoalService.findAllByUser(user)
                .stream()
                .map(f -> {
                    FinancialGoalDTO financialGoalDTO = ModelMapperUtil.map(f, FinancialGoalDTO.class);
                    List<FinancialGoalHistoryDTO> financialGoalHistoryList = financialGoalHistoryService.findHistoryByFinancialGoal(f)
                            .stream()
                            .map(financialGoalHistory -> ModelMapperUtil.map(financialGoalHistory, FinancialGoalHistoryDTO.class))
                            .collect(Collectors.toList());
                    financialGoalDTO.setFinancialGoalHistoryDTOList(financialGoalHistoryList);
                    return financialGoalDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public FinancialGoalDTO saveFinancialGoalHistory(FinancialGoalHistoryCreateDTO financialGoalHistoryCreateDTO) {
        FinancialGoal financialGoal = financialGoalService.findFinancialGoalById(financialGoalHistoryCreateDTO.getFinancialGoalId());
        FinancialGoalHistory financialGoalHistory = new FinancialGoalHistory();
        financialGoalHistory.setFinancialGoal(financialGoal);
        financialGoalHistory.setCreatedAt(LocalDateTime.now());
        financialGoalHistory.setSum(financialGoalHistoryCreateDTO.getSum());
        financialGoalHistoryService.save(financialGoalHistory);

        FinancialGoalDTO financialGoalDTO = ModelMapperUtil.map(financialGoal, FinancialGoalDTO.class);
        List<FinancialGoalHistory> financialGoalHistoryList = financialGoalHistoryService.findHistoryByFinancialGoal(financialGoal);
        BigDecimal financialGoalHistorySum = financialGoalHistoryList
                .stream()
                .map(FinancialGoalHistory::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (financialGoalHistorySum.compareTo(financialGoal.getSum()) == 0) {
            financialGoal.setIsAchieved(true);
        }

        FinancialGoal dbFinancialGoal = financialGoalService.save(financialGoal);
        List<FinancialGoalHistoryDTO> financialGoalHistoryDTOList = financialGoalHistoryService.findHistoryByFinancialGoal(dbFinancialGoal)
                .stream()
                .map(f -> ModelMapperUtil.map(f, FinancialGoalHistoryDTO.class))
                .collect(Collectors.toList());
        financialGoalDTO.setFinancialGoalHistoryDTOList(financialGoalHistoryDTOList);
        return financialGoalDTO;
    }

    @Override
    public FinancialGoalDTO getFinancialGoalById(Long financialGoalId) {
        FinancialGoal financialGoal = financialGoalService.findFinancialGoalById(financialGoalId);
        FinancialGoalDTO financialGoalDTO = ModelMapperUtil.map(financialGoal, FinancialGoalDTO.class);
        List<FinancialGoalHistoryDTO> financialGoalHistoryList = financialGoalHistoryService.findHistoryByFinancialGoal(financialGoal)
                .stream()
                .map(f -> ModelMapperUtil.map(f, FinancialGoalHistoryDTO.class))
                .collect(Collectors.toList());
        financialGoalDTO.setFinancialGoalHistoryDTOList(financialGoalHistoryList);
        return financialGoalDTO;
    }
}
