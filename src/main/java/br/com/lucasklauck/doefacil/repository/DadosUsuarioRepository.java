package br.com.lucasklauck.doefacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucasklauck.doefacil.domain.DadosUsuario;

@Repository
public interface DadosUsuarioRepository extends JpaRepository<DadosUsuario, Long>{
	
}
