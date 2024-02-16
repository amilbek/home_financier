package kz.app.home_financier.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "incomes")
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

    private LocalDateTime createdAt;
}
