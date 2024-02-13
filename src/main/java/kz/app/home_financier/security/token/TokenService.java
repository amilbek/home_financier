package kz.app.home_financier.security.token;

import io.jsonwebtoken.impl.DefaultClaims;
import kz.app.home_financier.model.dto.TokenResponse;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
public interface TokenService {
    boolean tokenValidation(String token);

    Principal getAuthentication(String token);

    String generateToken(Map<String,Object> claims, Integer expirationValue, Integer duration);

    TokenResponse generateTokensResponse(Map<String,Object> claims);

    DefaultClaims getClaimsFromToken(String token);


}
