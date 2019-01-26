package com.ascending.blair.extend.security;

import com.ascending.blair.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_AUDIANCE = "audience";
    private static final String CLAIM_KEY_CREATED = "created";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_TABLET = "tablet";
    private static final String AUDIENCE_MOBILE = "mobile";

    private String secret = "secret";

    private Long expiration = 86400L;

    private Date generateExpirationateDate(){
        return new Date(System.currentTimeMillis() + expiration*100);
    }

    public String generateAudience (Device device){
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()){
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()){
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()){
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    public String generateToken(UserDetails userDetails, Device device){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_AUDIANCE, generateAudience(device));
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);

    }

    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationateDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token){
        String username;
        try{
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token){
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        }catch (Exception e){
            expiration = null;
        }
        return expiration;
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);

        return (
                username.equals(user.getUsername())
                    && !isTokenExpired(token)
                );

    }


}
