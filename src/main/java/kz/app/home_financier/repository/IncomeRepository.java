package kz.app.home_financier.repository;

import kz.app.home_financier.model.entity.Income;
import kz.app.home_financier.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findAllByUser(User user);

    Optional<Income> findById(Long id);
}
