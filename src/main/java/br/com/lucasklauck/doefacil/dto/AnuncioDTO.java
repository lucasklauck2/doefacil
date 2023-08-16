package br.com.lucasklauck.doefacil.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AnuncioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	
	private String descricao;
	
	private BigDecimal quantidade;
	
	private Boolean aberto;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
	
	private List<String> listaImagens = new ArrayList<>();
	
	private Date dataCadastro;

}
