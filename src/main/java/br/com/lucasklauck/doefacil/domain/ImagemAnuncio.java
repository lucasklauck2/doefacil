package br.com.lucasklauck.doefacil.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name ="tb_imagemanuncio")
public class ImagemAnuncio {

	@Id
	@GeneratedValue
	@Column(name = "id_imagemanuncio")
	private Long id;
	
	@Column(name = "cd_anuncio", nullable = false, length = 2)
	private Long codigoAnuncio;
	
	@Column(name = "tx_imagembase64", nullable = false)
	private String imagemBase64;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_manutencao")
	private Date dataManutencao;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_anuncio", referencedColumnName = "id_anuncio", nullable = false, insertable = false, updatable = false)
	private Anuncio anuncio;

}
