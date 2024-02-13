package kz.app.home_financier.mapper;

import kz.app.home_financier.model.dto.UserDTO;
import kz.app.home_financier.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {

    UserDTO entityToDto(User user);
}
