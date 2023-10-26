package model;

import java.time.LocalDate;

public class User {
	private int id;
	private String usuario;
	private String senha;
	private String nome;
	private String email;
	private String descricao;
	private LocalDate dataNascimento;
	private boolean gerenciador;
	
	public User() {
		this.id = -1;
		this.usuario = "";
		this.senha = "";
		this.nome = "";
		this.email = "";
		this.setDescricao("");
		this.dataNascimento = LocalDate.now();
		this.gerenciador = false;
	}
	
	public User(int id, String usuario, String senha, String nome, String email, String descricao,  LocalDate dataNascimento, boolean gerenciador) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.dataNascimento = dataNascimento;
		this.gerenciador = gerenciador;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getGerenciador(){
		return gerenciador;
	}
	public void setGerenciador(boolean gerenciador){
		this.gerenciador = gerenciador;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
