package kz.app.home_financier.security.token;

import io.jsonwebtoken.impl.DefaultClaims;
import kz.app.home_financier.constants.ClaimKeysConstants;
import kz.app.home_financier.exception.UserBlockedAccessDeniedException;
import kz.app.home_financier.security.UserDetailsServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenAuthenticationManager implements AuthenticationManager {

    private final UserDetailsServiceImpl userDetailsService;
    private final TokenService tokenService;

    public TokenAuthenticationManager(@Qualifier("userDetailsServiceImpl") UserDetailsServiceImpl userDetailsService, TokenService tokenService) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return processAuthentication((TokenAuthentication) authentication);
    }

    public TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationException {
        String token = authentication.getToken();
        if (!tokenService.tokenValidation(token))
            throw new AuthenticationServiceException("Bad or expired token");
        return buildFullTokenAuthentication(authentication, token);
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, String token) {
        DefaultClaims claims = tokenService.getClaimsFromToken(token);
        if (Boolean.TRUE.equals(claims.get(ClaimKeysConstants.IS_REFRESH_TOKEN, Boolean.class))) {
            throw new AuthenticationServiceException("This is refresh token try access token");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get(ClaimKeysConstants.USERNAME, String.class));
        if (!userDetails.isAccountNonLocked()) throw new UserBlockedAccessDeniedException("User is blocked");

        return new TokenAuthentication(authentication.getToken(), true, userDetails);
    }
}