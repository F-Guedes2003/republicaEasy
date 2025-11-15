package org.republica.easy.republicaeasy.controller;

import org.republica.easy.republicaeasy.Entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/auth")
public class Auth {

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {

    }
}
