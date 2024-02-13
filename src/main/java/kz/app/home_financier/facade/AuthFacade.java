package kz.app.home_financier.facade;

import kz.app.home_financier.model.dto.SignInDTO;
import kz.app.home_financier.model.dto.SignUpDTO;
import kz.app.home_financier.model.dto.TokenResponse;
import kz.app.home_financier.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthFacade {

    UserDTO signUp(SignUpDTO signUpDTO);

    TokenResponse signIn(SignInDTO signInDTO);
}
