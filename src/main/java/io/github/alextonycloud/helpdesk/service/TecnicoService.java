package io.github.alextonycloud.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextonycloud.helpdesk.domain.Pessoa;
import io.github.alextonycloud.helpdesk.domain.Tecnico;
import io.github.alextonycloud.helpdesk.repositories.PessoaRepository;
import io.github.alextonycloud.helpdesk.repositories.TecnicoRepository;
import io.github.alextonycloud.helpdesk.service.exceptions.DataIntegrityViolationException;
import io.github.alextonycloud.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	private final TecnicoRepository repository;
	private final PessoaRepository pessoaRepository;

	public TecnicoService(TecnicoRepository repository, PessoaRepository pessoaRepository) {
		this.repository = repository;
		this.pessoaRepository = pessoaRepository;
	}
	
	public Tecnico findById(Integer id) {
		 Optional<Tecnico> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(Tecnico tecnico) {
		validByCPFAndEmail(tecnico);
		tecnico.setId(null);
		return repository.save(tecnico);
	}
	
	public Tecnico update(Integer id, Tecnico tecnico) {
		tecnico.setId(id);
		Tecnico OldTecnico = findById(id);
		validByCPFAndEmail(tecnico);
		OldTecnico = tecnico;
		return repository.save(OldTecnico);
	}

	private void validByCPFAndEmail(Tecnico tecnico) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnico.getCpf());
		if(obj.isPresent() && obj.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = pessoaRepository.findByEmail(tecnico.getEmail());
		if(obj.isPresent() && obj.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

	public void delete(Integer id) {
		repository.findById(id).map(tecnico -> {
			if(tecnico.getChamados().size() > 0) throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
			repository.delete(tecnico);
			return tecnico;
		}).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}
}
