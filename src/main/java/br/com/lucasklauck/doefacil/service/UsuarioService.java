package br.com.lucasklauck.doefacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lucasklauck.doefacil.domain.DadosUsuario;
import br.com.lucasklauck.doefacil.domain.Usuario;
import br.com.lucasklauck.doefacil.dto.AlteracaoSenhaDTO;
import br.com.lucasklauck.doefacil.dto.RetornoAutenticacaoDTO;
import br.com.lucasklauck.doefacil.dto.UsuarioDTO;
import br.com.lucasklauck.doefacil.repository.DadosUsuarioRepository;
import br.com.lucasklauck.doefacil.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DadosUsuarioRepository dadosUsuarioRepository;

	public void alterarSenha(String token, AlteracaoSenhaDTO alteracaoSenhaDTO) {

		String email = JWTTokenAutenticacaoService.extractEmailFromToken(token);

		Usuario usuario = usuarioRepository.findByEmail(email);

		if (usuario == null) {

			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		boolean senhaIgual = passwordEncoder.matches(alteracaoSenhaDTO.getSenhaAntiga(), usuario.getSenha());

		if (senhaIgual) {

			usuario.setSenha(passwordEncoder.encode(alteracaoSenhaDTO.getSenhaNova()));

			usuarioRepository.save(usuario);

		} else {

			throw new RuntimeException("Senha incorreta!");
		}
	}

	public RetornoAutenticacaoDTO cadastrar(UsuarioDTO usuarioDTO) {

		validarCamposObrigatorios(usuarioDTO);

		if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {

			throw new RuntimeException("E-mail já cadastrado. Faça login!");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		
		usuario = usuarioRepository.save(usuario);
		
		DadosUsuario dadosUsuario = new DadosUsuario();
		dadosUsuario.setCodigoUsuario(usuario.getId());
		dadosUsuario.setNome(usuarioDTO.getNome());
		dadosUsuario.setEndereco(usuarioDTO.getEndereco());
		dadosUsuario.setCodigoCidade(usuarioDTO.getCodigoCidade());
		
		dadosUsuarioRepository.save(dadosUsuario);

		return new RetornoAutenticacaoDTO(JWTTokenAutenticacaoService.getJWTToken(usuario.getEmail()));
	}
	
	public Long extrairCodigoUsuarioDeToken(String token) {
		
		String email = JWTTokenAutenticacaoService.extractEmailFromToken(token);
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null) {
			
			throw new RuntimeException("Usuário não encontrado");
		}
		
		return usuario.getId();
	}

	private void validarCamposObrigatorios(UsuarioDTO usuarioDTO) {

		if (usuarioDTO == null || usuarioDTO.getNome() == null || usuarioDTO.getEmail() == null
				|| usuarioDTO.getSenha() == null) {

			throw new RuntimeException(
					"Algum campo obrigatório está faltando. Por favor, vefifique as informações enviadas.");
		}

	}
}
