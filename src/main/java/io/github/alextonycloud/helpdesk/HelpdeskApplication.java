package io.github.alextonycloud.helpdesk;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.alextonycloud.helpdesk.domain.Chamado;
import io.github.alextonycloud.helpdesk.domain.Cliente;
import io.github.alextonycloud.helpdesk.domain.Tecnico;
import io.github.alextonycloud.helpdesk.domain.enums.Perfil;
import io.github.alextonycloud.helpdesk.domain.enums.Prioridade;
import io.github.alextonycloud.helpdesk.domain.enums.Status;
import io.github.alextonycloud.helpdesk.repositories.ChamadoRepository;
import io.github.alextonycloud.helpdesk.repositories.ClienteRepository;
import io.github.alextonycloud.helpdesk.repositories.PessoaRepository;
import io.github.alextonycloud.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	
	private final TecnicoRepository tecnicoRepository;
	private final ClienteRepository clienteRepository;
	private final ChamadoRepository chamadoRepository;
	
	

	public HelpdeskApplication(TecnicoRepository tecnicoRepository, ClienteRepository clienteRepository, ChamadoRepository chamadoRepository) {
		this.tecnicoRepository = tecnicoRepository;
		this.clienteRepository = clienteRepository;
		this.chamadoRepository = chamadoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Valdir Czar", "82786979064", "valdir@mail.com", "123");
		tec1.addPerfis(Perfil.TECNICO);
		
		Cliente cli1= new Cliente(null, "linus Torlvatz", "02799954073", "torvaltz@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}

}
