package kz.app.home_financier.service;

import kz.app.home_financier.constants.RoleEnums;
import kz.app.home_financier.model.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface DictionaryService {

    Role findRoleByCode(RoleEnums code);
}
