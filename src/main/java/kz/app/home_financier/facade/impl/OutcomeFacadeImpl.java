package kz.app.home_financier.facade.impl;

import kz.app.home_financier.facade.OutcomeFacade;
import kz.app.home_financier.model.dto.InOutComeCreateDTO;
import kz.app.home_financier.model.dto.InOutComeDTO;
import kz.app.home_financier.model.entity.Category;
import kz.app.home_financier.model.entity.Outcome;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.service.CategoryService;
import kz.app.home_financier.service.OutcomeService;
import kz.app.home_financier.service.UserService;
import kz.app.home_financier.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutcomeFacadeImpl implements OutcomeFacade {

    private final OutcomeService outcomeService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public InOutComeDTO saveOutcome(InOutComeCreateDTO inOutComeCreateDTO) {
        User user = userService.getAuth();
        Category category = categoryService.findCategoryById(inOutComeCreateDTO.getCategoryId());
        Outcome outcome = new Outcome();
        outcome.setSum(inOutComeCreateDTO.getSum());
        outcome.setCategory(category);
        outcome.setComment(inOutComeCreateDTO.getComment());
        outcome.setUser(user);
        outcome.setCreatedAt(LocalDateTime.now());
        Outcome dbOutcome = outcomeService.save(outcome);
        return ModelMapperUtil.map(dbOutcome, InOutComeDTO.class);
    }

    @Override
    public List<InOutComeDTO> getAllOutcomesByUser() {
        User user = userService.getAuth();
        return outcomeService.findAllIncomesByUser(user)
                .stream()
                .sorted(Comparator.comparing(Outcome::getCreatedAt).reversed())
                .map(i -> ModelMapperUtil.map(i, InOutComeDTO.class))
                .collect(Collectors.toList());
    }
}
