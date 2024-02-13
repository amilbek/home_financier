package kz.app.home_financier.facade.impl;

import kz.app.home_financier.constants.RoleEnums;
import kz.app.home_financier.exception.AccessForbiddenException;
import kz.app.home_financier.exception.CustomException;
import kz.app.home_financier.facade.AuthFacade;
import kz.app.home_financier.mapper.UserMapper;
import kz.app.home_financier.model.dto.SignInDTO;
import kz.app.home_financier.model.dto.SignUpDTO;
import kz.app.home_financier.model.dto.TokenResponse;
import kz.app.home_financier.model.dto.UserDTO;
import kz.app.home_financier.model.entity.Role;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.security.token.TokenService;
import kz.app.home_financier.service.DictionaryService;
import kz.app.home_financier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static kz.app.home_financier.constants.ClaimKeysConstants.*;
import static kz.app.home_financier.constants.CommonErrorEnums.*;
import static kz.app.home_financier.util.UserSignUpValidation.*;

@Service
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {

    private final UserService userService;
    private final DictionaryService dictionaryService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        checkUserValidations(signUpDTO);
        User user = new User();
        user.setFirstName(signUpDTO.getFirstName());
        user.setLastName(signUpDTO.getLastName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        user.setRoles(Collections.singletonList(dictionaryService.findRoleByCode(RoleEnums.ROLE_USER)));
        return userMapper.entityToDto(userService.save(user));
    }

    @Override
    public TokenResponse signIn(SignInDTO signInDTO) {
        User user = userService.findUserByEmail(signInDTO.getEmail());
        if (!bCryptPasswordEncoder.matches(signInDTO.getPassword(), user.getPassword()))
            throw new CustomException(INVALID_USER_CREDENTIALS.name());
        return generateTokens(user);
    }

    private void checkUserValidations(SignUpDTO signUpDTO) {
        List<String> errors = new ArrayList<>();
        if (userService.existsUserByEmail(signUpDTO.getEmail())) {
            errors.add(String.valueOf(EMAIL_EXISTS));
        }
        if (!isValidEmail(signUpDTO.getEmail())) {
            errors.add(String.valueOf(INVALID_EMAIL));
        }
        if (!isValidPassword(signUpDTO.getPassword())) {
            errors.add(String.valueOf(INVALID_PASSWORD));
        }
        if (!isValidPasswordConfirmation(signUpDTO)) {
            errors.add(String.valueOf(INVALID_PASSWORD_CONFIRMATION));
        }
        if (!errors.isEmpty()) {
            throw new CustomException(String.valueOf(errors));
        }
    }

    private TokenResponse generateTokens(User user) {
        if (nonNull(user.getDeletedAt())) throw new AccessForbiddenException(USER_BLOCKED);

        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, user.getId());
        claims.put(USERNAME, user.getEmail());
        claims.put(ROLES, user.getRoles().stream().map(Role::getCode).collect(Collectors.toList()));

        return tokenService.generateTokensResponse(claims);
    }
}
