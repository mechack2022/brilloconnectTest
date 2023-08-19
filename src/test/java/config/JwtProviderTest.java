package config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JwtProviderTest {

    private JwtProvider jwtProvider;

    @BeforeEach
    public void setUp() {
        jwtProvider = new JwtProvider();
    }

    @Test
    public void testVerifyJWTWithValidToken() {
        String jwt = jwtProvider.generateJWT("john_doe");
        assertEquals("verification pass", jwtProvider.verifyJWT(jwt));
    }

    @Test
    public void testVerifyJWTWithInvalidToken() {
        String invalidJwt = "invalid_token";
        assertEquals("verification fails", jwtProvider.verifyJWT(invalidJwt));
    }

}
