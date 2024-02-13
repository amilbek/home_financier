package kz.app.home_financier.repository;

import kz.app.home_financier.constants.RoleEnums;
import kz.app.home_financier.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByCode(RoleEnums code);
}
