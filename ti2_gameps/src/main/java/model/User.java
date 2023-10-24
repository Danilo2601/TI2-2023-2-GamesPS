package model;

public class User {
	private int id;
	private String usuario;
	private String senha;
	private String nome;
	private String email;
	private int idade;
	private boolean gerenciador;
	
	public User() {
		this.id = -1;
		this.usuario = "";
		this.senha = "";
		this.nome = "";
		this.email = "";
		this.idade = 18;
		this.gerenciador = false;
	}
	
	public User(int id, String usuario, String senha, String nome, String email,  int idade, boolean gerenciador) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
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
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
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
	
	
	
}
