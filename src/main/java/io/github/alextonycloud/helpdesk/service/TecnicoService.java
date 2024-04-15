package io.github.alextonycloud.helpdesk.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextonycloud.helpdesk.domain.Tecnico;
import io.github.alextonycloud.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	private final TecnicoRepository repository;

	public TecnicoService(TecnicoRepository repository) {
		this.repository = repository;
	}
	
	public Tecnico findById(Integer id) {
		 Optional<Tecnico> obj = repository.findById(id);
		 return obj.orElse(null);
	}
	
}
