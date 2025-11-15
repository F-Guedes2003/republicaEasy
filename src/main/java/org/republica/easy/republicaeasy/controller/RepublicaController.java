package org.republica.easy.republicaeasy.controller;

import org.republica.easy.republicaeasy.Entities.Republica;
import org.republica.easy.republicaeasy.repository.RepublicaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1")
public class RepublicaController {
    private final RepublicaRepository repository;

    RepublicaController(RepublicaRepository repository){
        this.repository = repository;
    }

    @PostMapping("/republica")
    Republica insertRepublica(@RequestBody Republica republica) {
        return repository.save(republica);
    }
}
