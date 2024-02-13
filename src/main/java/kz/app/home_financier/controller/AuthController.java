package kz.app.home_financier.controller;

import kz.app.home_financier.facade.AuthFacade;
import kz.app.home_financier.model.dto.SignInDTO;
import kz.app.home_financier.model.dto.SignUpDTO;
import kz.app.home_financier.model.dto.TokenResponse;
import kz.app.home_financier.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signup(@Validated @RequestBody SignUpDTO signUpDTO) {
        return ResponseEntity.ok(authFacade.signUp(signUpDTO));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> signIn(@Validated @RequestBody SignInDTO signInDTO) {
        return ResponseEntity.ok(authFacade.signIn(signInDTO));
    }
}
