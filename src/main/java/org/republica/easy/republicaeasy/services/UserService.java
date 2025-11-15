package org.republica.easy.republicaeasy.services;

import org.republica.easy.republicaeasy.Entities.User;
import org.republica.easy.republicaeasy.ErrorType;
import org.republica.easy.republicaeasy.repositories.UserRepository;
import org.republica.easy.republicaeasy.util.FieldErrorMessageGenerator;
import org.republica.easy.republicaeasy.util.MissingFieldState;
import org.republica.easy.republicaeasy.util.RepeatedFieldState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
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
