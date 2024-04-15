package io.github.alextonycloud.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.alextonycloud.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
