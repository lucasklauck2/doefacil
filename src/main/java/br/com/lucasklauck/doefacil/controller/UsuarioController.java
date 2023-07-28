package br.com.lucasklauck.doefacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lucasklauck.doefacil.dto.AlteracaoSenhaDTO;
import br.com.lucasklauck.doefacil.dto.RetornoAutenticacaoDTO;
import br.com.lucasklauck.doefacil.dto.UsuarioDTO;
import br.com.lucasklauck.doefacil.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@CrossOrigin
	@PostMapping("/cadastrar")
	public ResponseEntity<RetornoAutenticacaoDTO> cadastrar(
			@RequestBody(required = true) UsuarioDTO usuarioDTO) {
		
		return ResponseEntity.ok(usuarioService.cadastrar(usuarioDTO));
	}

	@CrossOrigin
	@PostMapping("/alterarSenha")
	public ResponseEntity<Void> alterarSenha(@RequestHeader("Authorization") String token,
			@RequestBody AlteracaoSenhaDTO alteracaoSenhaDTO) {

		usuarioService.alterarSenha(token, alteracaoSenhaDTO);

		return ResponseEntity.ok().build();
	}
}
