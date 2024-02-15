package kz.app.home_financier.model.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "financial_goals")
@Where(clause = "deleted_at is null")
public class FinancialGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goal;

    private BigDecimal sum;

    @ManyToOne
    private User user;

    private Boolean isAchieved;

    private LocalDateTime deletedAt;
}
