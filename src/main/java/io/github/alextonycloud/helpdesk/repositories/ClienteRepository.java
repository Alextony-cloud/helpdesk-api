package io.github.alextonycloud.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.alextonycloud.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
