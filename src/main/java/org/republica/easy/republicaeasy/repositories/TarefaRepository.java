package org.republica.easy.republicaeasy.repositories;

import org.republica.easy.republicaeasy.Entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {

    List<Tarefa> findByRepublicaIdAndFinalizadaFalse(UUID republicaId);
}
