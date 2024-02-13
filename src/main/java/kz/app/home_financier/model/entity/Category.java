package kz.app.home_financier.model.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
@Where(clause = "deleted_at is null")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean customCategory;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;

    private Boolean isIncome;

    private Boolean isOutcome;
}
