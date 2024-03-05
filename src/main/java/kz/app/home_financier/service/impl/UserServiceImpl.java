package kz.app.home_financier.service.impl;

import javax.persistence.EntityNotFoundException;
import kz.app.home_financier.model.entity.User;
import kz.app.home_financier.repository.UserRepository;
import kz.app.home_financier.security.token.TokenAuthentication;
import kz.app.home_financier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
    }

    @Override
    public User getAuth() {
        TokenAuthentication authentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getUser();
    }

    @Override
    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
    }
}
