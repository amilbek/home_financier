package kz.app.home_financier.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.app.home_financier.constants.RoleEnums;
import kz.app.home_financier.model.entity.Role;
import kz.app.home_financier.repository.RoleRepository;
import kz.app.home_financier.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByCode(RoleEnums code) {
        return roleRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with code " + code));
    }
}
