package org.republica.easy.republicaeasy.controller;

import org.republica.easy.republicaeasy.DTOS.LoginDto;
import org.republica.easy.republicaeasy.DTOS.UserRegisterDto;
import org.republica.easy.republicaeasy.Entities.LoginResponse;
import org.republica.easy.republicaeasy.Entities.RefreshRequest;
import org.republica.easy.republicaeasy.Entities.User;
import org.republica.easy.republicaeasy.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/auth")
public class Auth {
    private final AuthService service;

    @Autowired
    public Auth(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto dto) {

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginInput) {
        return service.login(loginInput);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshRequest refreshInput) {
        return service.refresh(refreshInput);
    }
}
