package io.github.alextonycloud.helpdesk.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextonycloud.helpdesk.domain.Chamado;
import io.github.alextonycloud.helpdesk.repositories.ChamadoRepository;
import io.github.alextonycloud.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	private final ChamadoRepository repository;

	public ChamadoService(ChamadoRepository repository) {
		this.repository = repository;
	}
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
	}
	
}
