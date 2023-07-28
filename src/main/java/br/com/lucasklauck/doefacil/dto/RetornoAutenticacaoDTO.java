package br.com.lucasklauck.doefacil.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetornoAutenticacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String authorization;

}
