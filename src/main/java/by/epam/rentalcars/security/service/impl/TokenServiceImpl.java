package by.epam.rentalcars.security.service.impl;

import by.epam.rentalcars.entity.User;
import by.epam.rentalcars.security.service.TokenService;
import by.epam.rentalcars.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("security.token.secret.key")
    private String tokenKey;

    @Autowired
    private UserService userService;

    @Override
    public String generateToken(String email, String password) {
        if (email == null || password == null)
            return null;
        User user = userService.findByEmail(email);
        Map<String, Object> tokenData = new HashMap<>();
        if (user != null && password.equals(user.password)) {
            tokenData.put("clientType", "user");
            tokenData.put("userID", user.id);
            tokenData.put("email", user.email);
            tokenData.put("token_create_date", LocalDateTime.now());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 60);
            tokenData.put("token_expiration_date", calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            return jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact();
        }
        System.out.println("Error during token generation");
        return null;
    }
}
