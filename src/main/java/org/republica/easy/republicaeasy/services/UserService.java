package org.republica.easy.republicaeasy.services;

import org.republica.easy.republicaeasy.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public ResponseEntity<String> register(User user) {
        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User data must be provided!");
        }

        var invalidFields = validateUser(user);

        if (invalidFields.isEmpty()) {
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

        if (user.getAddress() == null) invalidFieldsList.add("address");

        if (user.getPassword() == null) invalidFieldsList.add("password");

        return invalidFieldsList;
    }
}
