package org.fragile;

import org.fragile.config.JwtProvider;
import org.fragile.entities.User;
import org.fragile.helpers.Validation;
import org.fragile.service.UserServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // instances of dependencies
        JwtProvider jwtProvider = new JwtProvider();
        Validation validation = new Validation();
        UserServiceImpl userServiceipml = new UserServiceImpl(jwtProvider, validation);

        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Please enter user email: ");
        String email = sc.nextLine();
        System.out.println("Please Enter your password: ");
        String password = sc.nextLine();
        System.out.println("Please Enter your Date of birth (yyyy-MM-dd): ");
        String dobString = sc.nextLine();

        LocalDate dob = null;
        try {
            dob = LocalDate.parse(dobString);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }

        if (dob != null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setDob(dob);

            userServiceipml.registerUser(newUser);
        }

        sc.close();

    }

}