package kz.app.home_financier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException() {
    }

    public AccessForbiddenException(Enum<?> value) {
        super(value.name());
    }

    public AccessForbiddenException(String message) {
        super(message);
    }
}
