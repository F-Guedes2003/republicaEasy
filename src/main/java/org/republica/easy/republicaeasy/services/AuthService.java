package org.republica.easy.republicaeasy.services;

import org.republica.easy.republicaeasy.DTOS.LoginDto;
import org.republica.easy.republicaeasy.Entities.LoginResponse;
import org.republica.easy.republicaeasy.Entities.RefreshRequest;
import org.republica.easy.republicaeasy.Entities.User;
import org.republica.easy.republicaeasy.repositories.UserRepository;
import org.republica.easy.republicaeasy.util.FieldErrorMessageGenerator;
import org.republica.easy.republicaeasy.util.MissingFieldState;
import org.republica.easy.republicaeasy.util.RepeatedFieldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository repository, AuthenticationManager authenticationManager, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> register(User user) {

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User data must be provided!");
        }

        List<String> missingFields = validateUserFields(user);
        List<String> repeatedFields = isRepeated(user);

        if (missingFields.isEmpty() && repeatedFields.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);
            return ResponseEntity
                    .status(201)
                    .body("User Created Successfully!");
        }

        FieldErrorMessageGenerator errorGenerator = missingFields.isEmpty()
                ? new FieldErrorMessageGenerator(new RepeatedFieldState())
                : new FieldErrorMessageGenerator(new MissingFieldState());

        String errorMessage = missingFields.isEmpty()
                ? errorGenerator.generateMessage(repeatedFields)
                : errorGenerator.generateMessage(missingFields);

        return ResponseEntity
                .badRequest()
                .body(errorMessage);
    }

    public ResponseEntity<LoginResponse> refresh(RefreshRequest req) {
        String refreshToken = req.refreshToken();
        String username = jwtService.extractUsername(refreshToken);
        if (refreshToken == null) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        var user = repository.findUserByEmail(username);

        if(user.isEmpty()) return ResponseEntity.status(403).build();

        var newToken = jwtService.generateToken(user.get());
        LoginResponse response = new LoginResponse();
        response.setToken(newToken);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<LoginResponse> login(LoginDto loginInput) {
        var authenticatedUser = authenticate(loginInput);

        String jwtToken = jwtService.generateToken(authenticatedUser.get());
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser.get());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    public Optional<User> authenticate(LoginDto loginInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInput.email(),
                        loginInput.password()
                )
        );

        return repository.findUserByEmail(loginInput.email());
    }

    public List<String> isRepeated(User user) {
        var email = repository.findUserByEmail(user.getEmail());
        var name = repository.findUserByName(user.getName());
        ArrayList<String> repeatedFields = new ArrayList<>();

        if(name.isPresent()) repeatedFields.add("name");
        if(email.isPresent()) repeatedFields.add("email");

        return repeatedFields;
    }

    public List<String> validateUserFields(User user) {
        ArrayList<String> invalidFieldsList = new ArrayList<>();

        if (user.getName() == null || user.getName().isBlank()) invalidFieldsList.add("name");

        if (user.getEmail() == null || user.getEmail().isBlank()) invalidFieldsList.add("email");

        if (user.getLocalization() == null) invalidFieldsList.add("localization");

        if (user.getPassword() == null || user.getPassword().isBlank()) invalidFieldsList.add("password");

        return invalidFieldsList;
    }
}
