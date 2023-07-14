package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@Column(name = "nome", nullable = true, length = 40)
	private String nome;
	
	@Column(name = "cidade", nullable = true, length = 30)
	private String cidade;
	
	@Email
	@Column(name = "email", nullable = true, length = 60)
	private String email;
	
	@Column(name = "cep", nullable = true, length = 10)
	private String cep;
	
	@Column(name = "enderco", nullable = true, length = 100)
	private String enderco;
	
	@Column(name = "pais", nullable = true, length = 15)
	private String pais;
	
	@Column(name = "usuario", nullable = true, length = 50)
	private String usuario;
	
	@Column(name = "telefone", nullable = true, length = 14)
	private String telefone;
	
	@Column(name = "data_Nascimento")
	private String dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = true)
	private Cargo cargoId;

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEnderco() {
		return enderco;
	}

	public void setEnderco(String enderco) {
		this.enderco = enderco;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cargo getCargoId() {
		return cargoId;
	}

	public void setCargoId(Cargo cargoId) {
		this.cargoId = cargoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	} 

}
