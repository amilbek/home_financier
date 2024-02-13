package kz.app.home_financier.security.token;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.app.home_financier.security.token.handler.CustomAuthenticationFailureHandler;
import kz.app.home_financier.security.token.handler.CustomAuthenticationSuccessHandler;
import kz.app.home_financier.security.token.handler.LoginRequestMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public TokenAuthenticationFilter() {
        super(new LoginRequestMatcher());
        setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        Authentication authentication;
        String token = httpServletRequest.getHeader("Authorization");
        authentication = getAuthenticationManager().authenticate(new TokenAuthentication(token));
        return authentication;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());
        super.doFilter(req, res, chain);
    }
}
