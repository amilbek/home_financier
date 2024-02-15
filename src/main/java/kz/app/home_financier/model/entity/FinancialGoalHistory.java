package kz.app.home_financier.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "financial_goal_history")
public class FinancialGoalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal sum;

    private LocalDateTime createdAt;

    @ManyToOne
    private FinancialGoal financialGoal;
}
