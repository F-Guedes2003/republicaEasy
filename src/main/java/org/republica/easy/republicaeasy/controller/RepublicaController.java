package org.republica.easy.republicaeasy.controller;

import org.republica.easy.republicaeasy.DTOS.RepublicaCriadaResponseDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoRequestDto;
import org.republica.easy.republicaeasy.DTOS.UsuarioLinkadoResponseDto;
import org.republica.easy.republicaeasy.Entities.Republica;
import org.republica.easy.republicaeasy.Entities.Tarefa;
import org.republica.easy.republicaeasy.repositories.RepublicaRepository;
import org.republica.easy.republicaeasy.services.RepublicaService;
import org.republica.easy.republicaeasy.services.TarefaService;
import org.republica.easy.republicaeasy.services.UserRepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/republica")
public class RepublicaController {

    private final RepublicaRepository repository;
    private final RepublicaService service;
    private final UserRepService userRepService;
    private final TarefaService tarefaService;

    public RepublicaController(
            RepublicaRepository repository,
            RepublicaService service,
            UserRepService userRepService,
            TarefaService tarefaService
    ) {
        this.repository = repository;
        this.service = service;
        this.userRepService = userRepService;
        this.tarefaService = tarefaService;
    }

    // Criar república
    @PostMapping
    ResponseEntity<RepublicaCriadaResponseDto> insertRepublica(@RequestBody Republica republica) {
        return service.register(republica);
    }

    // Adicionar usuário na república
    @PostMapping("/addUser")
    ResponseEntity<UsuarioLinkadoResponseDto> addEstudanteNaRepublica(
            @RequestBody UsuarioLinkadoRequestDto req) {
        return userRepService.linkaUsuarioERepublica(req);
    }

    // 1. Listar todas as repúblicas
    @GetMapping
    public ResponseEntity<List<Republica>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Republica> getById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Editar república
    @PutMapping("/{id}")
    public ResponseEntity<Republica> updateRepublica(
            @PathVariable UUID id,
            @RequestBody Republica updates) {

        return repository.findById(id)
                .map(rep -> {
                    rep.setName(updates.getName());
                    rep.setDescription(updates.getDescription());
                    rep.setLocalization(updates.getLocalization());
                    rep.setImageUrl(updates.getImageUrl());
                    rep.setLimitSpot(updates.getLimitSpot());
                    rep.setContact(updates.getContact());
                    return ResponseEntity.ok(repository.save(rep));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Adicionar tarefa
    @PostMapping("/{republicaId}/tarefa/{usuarioId}")
    public ResponseEntity<Tarefa> addTarefa(
            @PathVariable UUID republicaId,
            @PathVariable UUID usuarioId,
            @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.addTarefa(republicaId, usuarioId, tarefa));
    }

    // 6. Editar tarefa
    @PutMapping("/tarefa/{tarefaId}")
    public ResponseEntity<Tarefa> updateTarefa(
            @PathVariable UUID tarefaId,
            @RequestBody Tarefa updates) {
        return ResponseEntity.ok(tarefaService.updateTarefa(tarefaId, updates));
    }

    // 7. Listar tarefas ativas
    @GetMapping("/{republicaId}/tarefas/ativas")
    public ResponseEntity<List<Tarefa>> listarTarefasAtivas(@PathVariable UUID republicaId) {
        return ResponseEntity.ok(tarefaService.listarAtivas(republicaId));
    }
}

