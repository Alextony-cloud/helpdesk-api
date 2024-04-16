package io.github.alextonycloud.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextonycloud.helpdesk.domain.Tecnico;
import io.github.alextonycloud.helpdesk.repositories.TecnicoRepository;
import io.github.alextonycloud.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	private final TecnicoRepository repository;

	public TecnicoService(TecnicoRepository repository) {
		this.repository = repository;
	}
	
	public Tecnico findById(Integer id) {
		 Optional<Tecnico> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
}
