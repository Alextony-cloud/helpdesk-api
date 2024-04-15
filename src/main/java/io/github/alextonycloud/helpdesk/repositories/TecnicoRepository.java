package io.github.alextonycloud.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.alextonycloud.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
