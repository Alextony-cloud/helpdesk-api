package io.github.alextonycloud.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.alextonycloud.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
