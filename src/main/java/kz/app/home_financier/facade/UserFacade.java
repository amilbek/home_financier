package kz.app.home_financier.facade;

import kz.app.home_financier.model.dto.UpdateUserDTO;
import kz.app.home_financier.model.dto.UserDTO;

public interface UserFacade {

    UserDTO getProfile();

    UserDTO updateUser(UpdateUserDTO updateUserDTO);

    void deleteUser();
}
