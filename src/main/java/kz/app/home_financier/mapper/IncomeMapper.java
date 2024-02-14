package kz.app.home_financier.mapper;

import kz.app.home_financier.model.dto.InOutComeDTO;
import kz.app.home_financier.model.entity.Income;
import org.springframework.stereotype.Service;

@Service
public interface IncomeMapper {

    InOutComeDTO entityToDto(Income income);
}
