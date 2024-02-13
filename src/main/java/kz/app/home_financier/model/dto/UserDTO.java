package kz.app.home_financier.model.dto;

import kz.app.home_financier.constants.RoleEnums;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleEnums> roles;
}
