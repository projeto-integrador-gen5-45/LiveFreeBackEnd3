package org.generation.liveFree.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//Indicar para o JDK que isso é uma tabela//
@Entity
@Table (name = "tb_usuario")
public class Usuario {
	
	
	//Definir os campos dessa tabela//
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O atributo nome é Obrigatório!")
	private String nome;

	@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caractéres")
	private String foto;

	@NotNull(message = "O atributo usuário é obrigatório!")
	@Email(message = "O atributo usuário deve ser um e-mail válido!")
	private String usuario;

	@NotBlank(message = "O atributo senha é obrigatório!")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
	private String senha;
	
	private String tipo;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Servico> servico;

	public Usuario(Long id, String nome, String foto, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.foto = foto;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Usuario() {}
	
	//Getters & Setters//


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Servico> getCategoria() {
		return servico;
	}

	public void setCategoria(List<Servico> servico) {
		this.servico = servico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}