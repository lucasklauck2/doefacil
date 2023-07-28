package br.com.lucasklauck.doefacil.dto;

import lombok.Data;

@Data
public class AlteracaoSenhaDTO {
	
	private String senhaAntiga;
	
	private String senhaNova;
}
