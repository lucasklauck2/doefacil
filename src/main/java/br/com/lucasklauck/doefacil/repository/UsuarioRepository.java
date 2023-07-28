package br.com.lucasklauck.doefacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucasklauck.doefacil.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public abstract Usuario findByEmail(String email);
	
	public abstract boolean existsByEmail(String email);
}
