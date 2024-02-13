package kz.app.home_financier.security.token.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class LoginRequestMatcher implements RequestMatcher {

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        return !httpServletRequest.getRequestURI().contains("/auth");
    }
}
