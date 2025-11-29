package org.republica.easy.republicaeasy.services;

import org.republica.easy.republicaeasy.Entities.Republica;
import org.republica.easy.republicaeasy.Entities.Tarefa;
import org.republica.easy.republicaeasy.Entities.User;
import org.republica.easy.republicaeasy.repositories.RepublicaRepository;
import org.republica.easy.republicaeasy.repositories.TarefaRepository;
import org.republica.easy.republicaeasy.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepo;
    private final RepublicaRepository republicaRepo;
    private final UserRepository userRepo;

    public TarefaService(
            TarefaRepository tarefaRepo,
            RepublicaRepository republicaRepo,
            UserRepository userRepo
    ) {
        this.tarefaRepo = tarefaRepo;
        this.republicaRepo = republicaRepo;
        this.userRepo = userRepo;
    }

    public Tarefa addTarefa(UUID republicaId, UUID usuarioId, Tarefa tarefa) {

        Republica rep = republicaRepo.findById(republicaId)
                .orElseThrow(() -> new RuntimeException("República não encontrada"));

        User user = userRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário responsável não encontrado"));

        tarefa.setRepublica(rep);
        tarefa.setUsuarioResponsavel(user);

        return tarefaRepo.save(tarefa);
    }

    public Tarefa updateTarefa(UUID tarefaId, Tarefa updates) {
        Tarefa tarefa = tarefaRepo.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefa.setTitulo(updates.getTitulo());
        tarefa.setFinalizada(updates.isFinalizada());

        return tarefaRepo.save(tarefa);
    }

    public List<Tarefa> listarAtivas(UUID republicaId) {
        return tarefaRepo.findByRepublicaIdAndFinalizadaFalse(republicaId);
    }
}
