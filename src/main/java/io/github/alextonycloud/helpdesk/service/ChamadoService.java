package io.github.alextonycloud.helpdesk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextonycloud.helpdesk.domain.Chamado;
import io.github.alextonycloud.helpdesk.domain.Cliente;
import io.github.alextonycloud.helpdesk.domain.Tecnico;
import io.github.alextonycloud.helpdesk.domain.dtos.ChamadoDTO;
import io.github.alextonycloud.helpdesk.domain.enums.Prioridade;
import io.github.alextonycloud.helpdesk.domain.enums.Status;
import io.github.alextonycloud.helpdesk.repositories.ChamadoRepository;
import io.github.alextonycloud.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	private final ChamadoRepository repository;
	private final TecnicoService tecnicoService;
	private final ClienteService clienteService;


	
	public ChamadoService(ChamadoRepository repository, TecnicoService tecnicoService, ClienteService clienteService) {
		super();
		this.repository = repository;
		this.tecnicoService = tecnicoService;
		this.clienteService = clienteService;
	}

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create( ChamadoDTO objDTO) {
		return repository.save(newChamado(objDTO));
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacao(obj.getObservacao());
		return chamado;
	}

	public Chamado update(Integer id, ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO); 
		return repository.save(oldObj);
	}
	
}
