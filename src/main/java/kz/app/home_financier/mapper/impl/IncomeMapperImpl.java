package kz.app.home_financier.mapper.impl;

import kz.app.home_financier.mapper.IncomeMapper;
import kz.app.home_financier.model.dto.CategoryDTO;
import kz.app.home_financier.model.dto.InOutComeDTO;
import kz.app.home_financier.model.entity.Income;
import kz.app.home_financier.util.ModelMapperUtil;
import org.springframework.stereotype.Service;

@Service
public class IncomeMapperImpl implements IncomeMapper {


    @Override
    public InOutComeDTO entityToDto(Income income) {
        CategoryDTO categoryDTO = ModelMapperUtil.map(income.getCategory(), CategoryDTO.class);

        InOutComeDTO inOutComeDTO = new InOutComeDTO();
        return null;
    }
}
