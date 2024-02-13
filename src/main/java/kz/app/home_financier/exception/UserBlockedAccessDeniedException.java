package kz.app.home_financier.exception;

import org.springframework.security.core.AuthenticationException;

public class UserBlockedAccessDeniedException extends AuthenticationException {

    public UserBlockedAccessDeniedException(String msg) {
        super(msg);
    }
}
