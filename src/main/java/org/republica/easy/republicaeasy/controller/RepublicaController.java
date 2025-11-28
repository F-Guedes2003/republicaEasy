package org.republica.easy.republicaeasy.controller;

import org.republica.easy.republicaeasy.DTOS.RepublicaCriadaResponseDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoRequestDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoResponseDto;
import org.republica.easy.republicaeasy.Entities.Republica;
import org.republica.easy.republicaeasy.repositories.RepublicaRepository;
import org.republica.easy.republicaeasy.services.RepublicaService;
import org.republica.easy.republicaeasy.services.UserRepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/republica")
public class RepublicaController {
    private final RepublicaRepository repository;
    private final UserRepService userRepService;
    private final RepublicaService service;

    RepublicaController(RepublicaRepository repository,
                        RepublicaService service,
                        UserRepService userRepService){
        this.service = service;
        this.repository = repository;
        this.userRepService = userRepService;
    }

    @PostMapping()
    ResponseEntity<RepublicaCriadaResponseDto> insertRepublica(@RequestBody Republica republica) {
        return service.register(republica);
    }

    @PostMapping("/addUser")
    ResponseEntity<UsuarioLinkadoResponseDto> addEstudanteNaRepublica(
            @RequestBody UsuarioLinkadoRequestDto req) {

            return userRepService.linkaUsuarioERepublica(req);
    }
}
