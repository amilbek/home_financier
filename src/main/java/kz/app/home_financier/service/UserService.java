package kz.app.home_financier.service;

import kz.app.home_financier.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User save(User user);

    Boolean existsUserByEmail(String email);

    User findUserByEmail(String email);

    User getAuth();
}
