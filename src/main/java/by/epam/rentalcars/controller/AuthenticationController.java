package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.Token;
import by.epam.rentalcars.entity.User;
import by.epam.rentalcars.security.service.TokenService;
import by.epam.rentalcars.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody User requestUser) {
        LOGGER.info("Start authentication");
        if (isNotEmpty(requestUser.email) && isNotEmpty(requestUser.getPassword())) {
            User user = userService.findByEmail(requestUser.email);
            if (user != null) {
                String token = tokenService.generateToken(user.email, requestUser.getPassword());
                if (token != null) {
                    LOGGER.info("Authentication successful! Returning token");
                    user.setPassword(EMPTY);
                    return new ResponseEntity<>(new Token(token, user), HttpStatus.OK);
                }
            }
        }
        LOGGER.error("Authentication failed");
        return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
    }
}