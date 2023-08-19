package org.fragile.service;
import config.JwtProvider;
import org.fragile.entities.User;
import org.fragile.helpers.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

public class UserServiceImplTest {

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private Validation validation;

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(jwtProvider, validation);
    }

    @Test
    public void testRegisterUserValidInput() {
        String input = "john_doe\njohn@example.com\nP@ssw0rd\n1990-01-01\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);



        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("P@ssw0rd");
        user.setDob(LocalDate.of(1990, 1, 1));

        when(validation.isValidEmail(anyString())).thenReturn(true);
        when(validation.isValidPassword(anyString())).thenReturn(true);
        when(validation.isUnder16(any(LocalDate.class))).thenReturn(false);
        when(jwtProvider.generateJWT(anyString())).thenReturn("mocked_jwt");
        when(jwtProvider.verifyJWT(anyString())).thenReturn("verification pass");
        when(jwtProvider.verifyJWT(anyString())).thenReturn("verification pass");

        userService.registerUser(user);


    }


}




