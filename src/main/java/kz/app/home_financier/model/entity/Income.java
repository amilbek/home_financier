package kz.app.home_financier.model.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "incomes")
@Where(clause = "deleted_at is null")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal sum;

    @OneToOne
    private Category category;

    private String comment;

    @ManyToOne
    private User user;
}
