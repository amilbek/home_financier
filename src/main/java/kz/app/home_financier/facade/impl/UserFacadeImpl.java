package kz.app.home_financier.facade.impl;

import kz.app.home_financier.exception.CustomException;
import kz.app.home_financier.facade.UserFacade;
import kz.app.home_financier.model.dto.UpdateUserDTO;
import kz.app.home_financier.model.dto.UserDTO;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.service.UserService;
import kz.app.home_financier.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    @Override
    public UserDTO getProfile() {
        return ModelMapperUtil.map(userService.getAuth(), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UpdateUserDTO updateUserDTO) {
        User user = userService.getAuth();
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        if (Boolean.TRUE.equals(userService.existsUserByEmail(updateUserDTO.getEmail()))) {
            if (user.getEmail().equals(updateUserDTO.getEmail())) {
                user.setEmail(updateUserDTO.getEmail());
            } else {
                throw new CustomException("email уже существует");
            }
        }
        user.setEmail(updateUserDTO.getEmail());
        return ModelMapperUtil.map(userService.save(user), UserDTO.class);
    }

    @Override
    public void deleteUser() {
        User user = userService.getAuth();
        user.setDeletedAt(LocalDateTime.now());
        userService.save(user);
    }
}
