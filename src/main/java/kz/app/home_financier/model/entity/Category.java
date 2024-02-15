package kz.app.home_financier.model.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean customCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Boolean isIncome;

    private Boolean isOutcome;
}
