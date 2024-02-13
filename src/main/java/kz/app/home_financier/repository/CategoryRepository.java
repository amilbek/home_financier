package kz.app.home_financier.repository;

import kz.app.home_financier.model.entity.Category;
import kz.app.home_financier.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByUserAndIsIncomeIsTrue(User user);

    List<Category> findByUserAndIsOutcomeIsTrue(User user);

    List<Category> findByCustomCategoryIsFalseAndIsIncomeIsTrue();

    List<Category> findByCustomCategoryIsFalseAndIsOutcomeIsTrue();
}
