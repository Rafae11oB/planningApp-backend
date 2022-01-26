package app.planningApp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationTimeInSeconds}")
    private Long expirationTimeInSeconds;

    /**
     * Generate token by email
     * WARNING: Expiration time calculating in UTC
     * @param email - user email
     * @return - jwt token
     */
    public String generateToken(String email){
        Date date = Date.from(LocalDateTime.now().plusSeconds(expirationTimeInSeconds).toInstant(ZoneOffset.of("+00:00")));
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        return true;
    }

    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
