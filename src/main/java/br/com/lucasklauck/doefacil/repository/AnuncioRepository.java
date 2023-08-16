package br.com.lucasklauck.doefacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucasklauck.doefacil.domain.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
	
}
