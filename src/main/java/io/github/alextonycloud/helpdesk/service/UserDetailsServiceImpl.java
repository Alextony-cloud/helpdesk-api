package io.github.alextonycloud.helpdesk.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.alextonycloud.helpdesk.domain.Pessoa;
import io.github.alextonycloud.helpdesk.repositories.PessoaRepository;
import io.github.alextonycloud.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final PessoaRepository repository;
	
	public UserDetailsServiceImpl(PessoaRepository repository) {
		this.repository = repository;
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> user = repository.findByEmail(email);
		if(user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
		}
		throw new UsernameNotFoundException(email);
	}

}
