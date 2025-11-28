package org.republica.easy.republicaeasy.repositories;

import org.republica.easy.republicaeasy.Entities.Republica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepublicaRepository extends JpaRepository<Republica, UUID> {
}
