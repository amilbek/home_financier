package kz.app.home_financier.mapper;

import kz.app.home_financier.model.dto.UserDTO;
import kz.app.home_financier.model.entity.Role;
import kz.app.home_financier.model.entity.User;

import java.util.stream.Collectors;

public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(
                user.getRoles()
                        .stream()
                        .map(Role::getCode)
                        .collect(Collectors.toList())
        );
        return userDTO;
    }
}
