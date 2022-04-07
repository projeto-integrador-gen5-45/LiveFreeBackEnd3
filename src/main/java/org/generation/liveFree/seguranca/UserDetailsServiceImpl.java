package org.generation.liveFree.seguranca;

import java.util.Optional;

import org.generation.liveFree.model.Usuario;
import org.generation.liveFree.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private @Autowired UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> optional = repository.findByUsuario(username);

		if (optional.isPresent()) {
			return new UserDetailsImpl(optional.get());
		} else {
			throw new UsernameNotFoundException("Usuario não existe");
		}
	}

}
