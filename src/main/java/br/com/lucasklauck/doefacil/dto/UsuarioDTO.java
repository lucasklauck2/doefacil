package br.com.lucasklauck.doefacil.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private String endereco;
	
	private Long codigoCidade;
	
	private String dataCadastro;
	
	private String dataManutencao;
}
