package io.github.alextonycloud.helpdesk.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.alextonycloud.helpdesk.domain.Chamado;
import io.github.alextonycloud.helpdesk.domain.dtos.ChamadoDTO;
import io.github.alextonycloud.helpdesk.service.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

	private final ChamadoService service;
	
	public ChamadoResource(ChamadoService service) {
		this.service = service;
	}

	@RequestMapping("/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado obj = service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
}
