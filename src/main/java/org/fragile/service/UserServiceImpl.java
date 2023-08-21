package org.fragile.service;

import org.fragile.config.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.fragile.entities.User;
import org.fragile.helpers.Validation;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {
    private final JwtProvider jwtProvider;
    private final Validation validation;


    @Override
    public void registerUser(User user) {

        String validationMessage = performValidations(user.getUsername(), user.getEmail(), user.getPassword(), user.getDob());
        if (validationMessage.equals("Validation passed")) {
            String jwt = jwtProvider.generateJWT(user.getUsername());
            System.out.println("Generated JWT: " + jwt);

            String verificationResult = jwtProvider.verifyJWT(jwt);
            System.out.println("JWT Verification: " + verificationResult);
        } else {
            System.out.println(validationMessage);
        }

    }

        private  String performValidations(String username, String email, String password, LocalDate dob) {

            StringBuilder validationErrors = new StringBuilder();
//
        if (username.length() < 4) {
            validationErrors.append("Username: not empty, min 4 characters\n");
        }


        if (email.isEmpty() || !validation.isValidEmail(email)) {
            validationErrors.append("Email: email not valid \n");
        }

        if (!validation.isValidPassword(password)) {
            validationErrors.append("Password: strong password required\n");
        }

        if (dob == null || validation.isUnder16(dob)) {
            validationErrors.append("Date of Birth: should be 16 years or greater\n");
        }

        if (validationErrors.length() > 0) {
            return validationErrors.toString();
        } else {
            return "Validation passed";
        }
    }

}
