package kz.app.home_financier.facade.impl;

import kz.app.home_financier.facade.IncomeFacade;
import kz.app.home_financier.model.dto.InOutComeCreateDTO;
import kz.app.home_financier.model.dto.InOutComeDTO;
import kz.app.home_financier.model.entity.Category;
import kz.app.home_financier.model.entity.Income;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.service.CategoryService;
import kz.app.home_financier.service.IncomeService;
import kz.app.home_financier.service.UserService;
import kz.app.home_financier.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeFacadeImpl implements IncomeFacade {

    private final IncomeService incomeService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public InOutComeDTO saveIncome(InOutComeCreateDTO inOutComeCreateDTO) {
        User user = userService.getAuth();
        Category category = categoryService.findCategoryById(inOutComeCreateDTO.getCategoryId());
        Income income = new Income();
        income.setSum(inOutComeCreateDTO.getSum());
        income.setCategory(category);
        income.setComment(inOutComeCreateDTO.getComment());
        income.setUser(user);
        Income dbIncome = incomeService.save(income);
        return ModelMapperUtil.map(dbIncome, InOutComeDTO.class);
    }

    @Override
    public List<InOutComeDTO> getAllIncomesByUser() {
        User user = userService.getAuth();
        return incomeService.findAllIncomesByUser(user)
                .stream()
                .map(i -> ModelMapperUtil.map(i, InOutComeDTO.class))
                .collect(Collectors.toList());
    }
}
