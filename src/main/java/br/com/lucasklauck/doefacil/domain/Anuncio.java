package br.com.lucasklauck.doefacil.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name ="tb_anuncio")
public class Anuncio {

	@Id
	@GeneratedValue
	@Column(name = "id_anuncio")
	private Long id;
	
	@Column(name = "cd_usuario", nullable = false, length = 2)
	private Long codigoUsuario;
	
	@Column(name = "tx_titulo", nullable = false)
	private String titulo;
	
	@Column(name = "tx_descricao")
	private String descricao;

	@Column(name = "nr_quantidade")
	private Long quantidade;
	
	@Column(name = "nr_latitude")
	private Long latitude;
	
	@Column(name = "nr_longitude")
	private Long longitude;
	
	@Column(name = "fl_aberto")
	private Boolean aberto;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_manutencao")
	private Date dataManutencao;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_usuario", referencedColumnName = "id_usuario", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;

}
