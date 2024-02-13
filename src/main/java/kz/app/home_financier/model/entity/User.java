package kz.app.home_financier.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@SQLRestriction("deleted_at is false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDateTime deletedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
