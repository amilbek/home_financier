package kz.app.home_financier.security.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import kz.app.home_financier.constants.ClaimKeysConstants;
import kz.app.home_financier.model.dto.TokenResponse;
import kz.app.home_financier.security.UserDetailsImpl;
import kz.app.home_financier.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Calendar;
import java.util.Map;

import static kz.app.home_financier.util.Utils.notNullOrEmptyStr;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.secret.password}")
    private String SECRET;

    private final String TOKEN_PREFIX = "Bearer ";
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public TokenServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @Override
    public boolean tokenValidation(String token) {
        DefaultClaims claims;
        try {
            if (token == null || token.isEmpty())
                return false;
            if (token.contains(TOKEN_PREFIX))
                token = token.replace(TOKEN_PREFIX, "");
            claims = (DefaultClaims) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return (notNullOrEmptyStr(claims.get(ClaimKeysConstants.ID, String.class)));
        } catch (ExpiredJwtException e){
            log.error("Token invalid exception. Exception message: {}", e.getLocalizedMessage());
            return false;
        } catch (Exception e) {
            log.error("Exception message: {}", e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public String generateToken(Map<String, Object> claims, Integer expirationValue, Integer duration) {
        Calendar calendar = Calendar.getInstance();
        claims.put(ClaimKeysConstants.TOKEN_CREATE_DATE, calendar.getTime());
        calendar.add(duration, expirationValue);
        claims.put(ClaimKeysConstants.TOKEN_EXPIRE_DATE, calendar.getTime());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    @Override
    public TokenResponse generateTokensResponse(Map<String, Object> claims) {
        String accessToken = TOKEN_PREFIX;
        String refreshToken = TOKEN_PREFIX;

        claims.put(ClaimKeysConstants.IS_REFRESH_TOKEN, false);
        accessToken += generateToken(claims, 1, Calendar.HOUR);
        claims.put(ClaimKeysConstants.IS_REFRESH_TOKEN, true);
        refreshToken += generateToken(claims, 1, Calendar.MONTH);

        return new TokenResponse(accessToken, refreshToken);

    }

    @Override
    public DefaultClaims getClaimsFromToken(String token) {
        DefaultClaims claims;
        try {
            if (token.contains(TOKEN_PREFIX))
                token = token.replace(TOKEN_PREFIX, "");
            claims = (DefaultClaims) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            log.error("Token claims invalid exception. Exception message: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Principal getAuthentication(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        String id = claims.getBody().get(ClaimKeysConstants.ID).toString();
        UserDetailsImpl userDetails = (UserDetailsImpl) this.userDetailsServiceImpl.loadUserByUsername(id);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
