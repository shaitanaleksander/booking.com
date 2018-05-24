package service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class JWTService {

    private final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private final String secret = "dataArt";
    private Algorithm algorithm;
    private static JWTService jwtService;

    private JWTService() {
        try {
            algorithm = Algorithm.HMAC256(secret);
        } catch (UnsupportedEncodingException e) {
            logger.error("UTF-8 encoding not supported", e);
        }
    }

    public String createToken(String id) {
        return JWT.create().withClaim("userId", id)
                .withIssuer("auth0")
                .sign(algorithm);
    }

    public String verifyToken(String token) throws JWTVerificationException {
        if (token == null) {
            throw new JWTVerificationException("token not present");
        }

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims().get("userId").asString();
    }

    public static JWTService getJwtService() {
        if (jwtService == null) jwtService = new JWTService();
        return jwtService;
    }
}

