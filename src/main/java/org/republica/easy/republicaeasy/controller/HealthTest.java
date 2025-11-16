package org.republica.easy.republicaeasy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthTest {
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Im working well hehehe");
    }
}
