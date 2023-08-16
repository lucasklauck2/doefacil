package br.com.lucasklauck.doefacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lucasklauck.doefacil.domain.Usuario;
import br.com.lucasklauck.doefacil.dto.AnuncioDTO;
import br.com.lucasklauck.doefacil.dto.RetornoAutenticacaoDTO;
import br.com.lucasklauck.doefacil.repository.UsuarioRepository;
import br.com.lucasklauck.doefacil.service.AnuncioService;
import br.com.lucasklauck.doefacil.service.JWTTokenAutenticacaoService;

@Controller
@RequestMapping("/anuncio")
public class AnuncioController {

	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@CrossOrigin
	@PostMapping("/criar")
	public ResponseEntity<RetornoAutenticacaoDTO> criar(@RequestHeader(value = "Authorization", required = true) String token,
			@RequestBody(required = true) AnuncioDTO anuncioDTO) {
		
		String email = JWTTokenAutenticacaoService.extractEmailFromToken(token);

		Usuario usuario = usuarioRepository.findByEmail(email);
		
		anuncioService.criar(anuncioDTO, usuario.getId());
		
		return ResponseEntity.ok().build();
	}
}
