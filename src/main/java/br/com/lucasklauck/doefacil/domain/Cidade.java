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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name ="tb_cidade")
public class Cidade {

	@Id
	@GeneratedValue
	@Column(name = "id_cidade")
	private Long id;
	
	@Column(name = "tx_siglaestado", nullable = false, length = 2)
	private String siglaEstado;
	
	@Column(name = "tx_nome", nullable = false)
	private String nome;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_manutencao")
	private Date dataManutencao;
	
    @OneToMany(mappedBy = "cidade")
    private List<DadosUsuario> listaDadosUsuario = new ArrayList<>();

}
