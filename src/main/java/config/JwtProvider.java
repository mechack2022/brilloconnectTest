package config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import org.fragile.contants.AppContants;

import java.util.Base64;

//@Builder
public class JwtProvider {
    public String generateJWT(String subject) {
//        String secretKey = "G1YT5AES305AOQSY196GF59JC54M8OLDG0P3HW3HKAQWVGAI7E";
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(AppContants.SECRET_KEY.getBytes()))
                .compact();
    }
    public  String verifyJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(AppContants.SECRET_KEY.getBytes()))
                    .parseClaimsJws(jwt)
                    .getBody();

            System.out.println("Parsed Claims: " + claims);

            return AppContants.VERIFICATION_PASS ;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return AppContants.VERIFICATION_FAIL;
        }
    }
}
