package io.swagger.customizacao.customizacao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
// Classe entidade mapeado para ser gravado no banco

@Entity
@Table(name = "cliente_tb")
public class ClienteEntity implements Serializable {

	private static final long serialVersionUID = -1744498845582568202L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titular;

	private String cpf;

	private String password;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) // Mapeamento um para muitos / cliente é a chave
	private List<ContaEntity> contas;							// primeria através campo mappedBy que vai ser ultizado
																// essa chave na tabe conta através CascadeType.ALL
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ContaEntity> getContas() {
		return contas;
	}

	public void setContas(List<ContaEntity> contas) {
		this.contas = contas;
	}

}///
