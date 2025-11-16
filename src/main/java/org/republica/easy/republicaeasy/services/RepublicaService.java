package org.republica.easy.republicaeasy.services;

import org.apache.tomcat.util.json.JSONParser;
import org.republica.easy.republicaeasy.Entities.Republica;
import org.republica.easy.republicaeasy.repositories.RepublicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RepublicaService {
    final private RepublicaRepository repository;

    @Autowired
    public RepublicaService(RepublicaRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<String> register(Republica republica) {
        if(republica == null) {
            return ResponseEntity.badRequest().body("Must provide republica data");
        }
        if(republica.toString().contains("null")){
            System.out.println(republica.toString());
            return ResponseEntity.badRequest().body("Some attribute is null");
        }
        repository.save(republica);
        return ResponseEntity.status(HttpStatus.CREATED).body("Republica created successfully");
    }
}
