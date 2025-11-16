package org.republica.easy.republicaeasy.controller;

import org.republica.easy.republicaeasy.Entities.Republica;
import org.republica.easy.republicaeasy.repositories.RepublicaRepository;
import org.republica.easy.republicaeasy.services.RepublicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1")
public class RepublicaController {
    private final RepublicaRepository repository;
    private final RepublicaService service;

    RepublicaController(RepublicaRepository repository,RepublicaService service){
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/republica")
    ResponseEntity<String> insertRepublica(@RequestBody Republica republica) {
        return service.register(republica);
    }
}
