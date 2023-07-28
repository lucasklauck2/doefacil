package br.com.lucasklauck.doefacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lucasklauck.doefacil.domain.Usuario;
import br.com.lucasklauck.doefacil.repository.UsuarioRepository;

@Service	
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = usuarioRepository.findByEmail(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
	}

	
}
