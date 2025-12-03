package org.republica.easy.republicaeasy.controller;

import org.republica.easy.republicaeasy.DTOS.UserResponseDto;
import org.republica.easy.republicaeasy.Entities.User;
import org.republica.easy.republicaeasy.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1 — Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(
                        new UserResponseDto(
                                user.getId(),
                                user.getEmail(),
                                user.getName(),
                                user.getRepublica() != null ? user.getRepublica().getId() : null,
                                user.getLocalization()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    // 2 — Buscar usuário por email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email) {
        return userRepository.findUserByEmail(email)
                .map(user ->  ResponseEntity.ok(
                            new UserResponseDto(
                            user.getId(),
                            user.getEmail(),
                            user.getName(),
                            user.getRepublica() != null ? user.getRepublica().getId() : null,
                            user.getLocalization()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3 — Editar usuário por ID
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable UUID id,
            @RequestBody User updates) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updates.getName());
                    userRepository.save(user);

                    return ResponseEntity.ok(new UserResponseDto(
                            user.getId(),
                            user.getEmail(),
                            user.getName(),
                            user.getRepublica() != null ? user.getRepublica().getId() : null,
                            user.getLocalization()
                    ));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
