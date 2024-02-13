package kz.app.home_financier.security.token.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.app.home_financier.model.dto.FailureResponse;
import kz.app.home_financier.util.BodyWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        FailureResponse failureResponse = new FailureResponse(
                HttpStatus.UNAUTHORIZED.value()
                , HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getLocalizedMessage(),
                httpServletRequest.getRequestURI());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        BodyWriter.bodyWriter(httpServletResponse.getWriter(), failureResponse, FailureResponse.class);
    }
}
