package org.republica.easy.republicaeasy.repositories;

import org.republica.easy.republicaeasy.Entities.Republica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepublicaRepository extends JpaRepository<Republica, UUID> {
}
