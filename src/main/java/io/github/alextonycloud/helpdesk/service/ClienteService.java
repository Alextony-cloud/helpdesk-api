package io.github.alextonycloud.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.alextonycloud.helpdesk.domain.Pessoa;
import io.github.alextonycloud.helpdesk.domain.Cliente;
import io.github.alextonycloud.helpdesk.repositories.PessoaRepository;
import io.github.alextonycloud.helpdesk.repositories.ClienteRepository;
import io.github.alextonycloud.helpdesk.service.exceptions.DataIntegrityViolationException;
import io.github.alextonycloud.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	private final ClienteRepository repository;
	private final PessoaRepository pessoaRepository;
	private final BCryptPasswordEncoder encoder;


	public ClienteService(ClienteRepository repository, PessoaRepository pessoaRepository, BCryptPasswordEncoder encoder) {
		this.repository = repository;
		this.pessoaRepository = pessoaRepository;
		this.encoder = encoder;
	}
	
	public Cliente findById(Integer id) {
		 Optional<Cliente> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(Cliente cliente) {
		cliente.setId(null);
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		validByCPFAndEmail(cliente);
		return repository.save(cliente);
	}
	
	public Cliente update(Integer id, Cliente cliente) {
		cliente.setId(id);
		Cliente OldCliente = findById(id);
		validByCPFAndEmail(cliente);
		OldCliente = cliente;
		return repository.save(OldCliente);
	}

	public void delete(Integer id) {
		repository.findById(id).map(cliente -> {
			if(cliente.getChamados().size() > 0) throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
			repository.delete(cliente);
			return cliente;
		}).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}
	
	private void validByCPFAndEmail(Cliente cliente) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(cliente.getCpf());
		if(obj.isPresent() && obj.get().getId() != cliente.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = pessoaRepository.findByEmail(cliente.getEmail());
		if(obj.isPresent() && obj.get().getId() != cliente.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
