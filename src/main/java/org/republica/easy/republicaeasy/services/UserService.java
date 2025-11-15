package org.republica.easy.republicaeasy.services;

import org.republica.easy.republicaeasy.Entities.User;
import org.republica.easy.republicaeasy.repositories.UserRepository;
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

        var invalidFields = validateUser(user);

        if (invalidFields.isEmpty()) {
            repository.save(user);
            return ResponseEntity
                    .status(201)
                    .body("User Created Successfully!");
        }

        return ResponseEntity
                .badRequest()
                .body("invalid fields: " + String.join(", ", invalidFields));
    }

    public List<String> validateUser(User user) {
        ArrayList<String> invalidFieldsList = new ArrayList<>();

        if (user.getName() == null) invalidFieldsList.add("name");

        if (user.getEmail() == null) invalidFieldsList.add("email");

        if (user.getLocalization() == null) invalidFieldsList.add("localization");

        System.out.println("city>: " + user.getLocalization());

        if (user.getPassword() == null) invalidFieldsList.add("password");

        return invalidFieldsList;
    }
}
