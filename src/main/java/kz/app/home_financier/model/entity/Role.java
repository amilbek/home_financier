package kz.app.home_financier.model.entity;

import javax.persistence.*;
import kz.app.home_financier.constants.RoleEnums;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnums code;

    @Override
    public String getAuthority() {
        return code.name();
    }
}
