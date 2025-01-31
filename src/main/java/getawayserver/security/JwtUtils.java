package getawayserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import getawayserver.security.variablesEnv.SecretKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class JwtUtils {

    @Autowired
    private SecretKeyConfig secretKeyConfig;

    public String getUsernameFromToken(String token) {
        if(token == null){
            throw new RuntimeException("Token nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKeyConfig.getSECRET_KEY());
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("ToDo List")
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error al verificar el token: " + e.getMessage());
        }
    }

    public boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        return (username !=null && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }

    public List<String> getAuthoritiesFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("authorities").asList(String.class);
    }
}
