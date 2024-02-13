package kz.app.home_financier.model.dto;

import lombok.Data;

@Data
public class SignUpDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;
}
