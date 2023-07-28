package br.com.lucasklauck.doefacil.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name ="tb_dadosusuario")
public class DadosUsuario {

	@Id
	@GeneratedValue
	@Column(name = "id_dadosusuario")
	private Long id;
	
	@Column(name = "cd_usuario", nullable = false)
	private Long codigoUsuario;
	
	@Column(name = "tx_nome", nullable = false)
	private String nome;
	
	@Column(name = "cd_cidade", nullable = false)
	private Long codigoCidade;
	
	@Column(name = "tx_endereco")
	private String endereco;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_manutencao")
	private Date dataManutencao;
	
	@OneToOne
	@JoinColumn(name = "cd_usuario", referencedColumnName = "id_usuario", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "cd_cidade", referencedColumnName = "id_cidade", insertable = false, updatable = false)
	private Cidade cidade;
	
	

}
