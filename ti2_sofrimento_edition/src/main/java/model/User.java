package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
	private int id;
	private String usuario;
	private String senha;
	private String nome;
	private String email;
	private String descricao;
	private LocalDate dataNascimento;
	private ArrayList<Integer> desejos;
	private boolean gerenciador;
	
	public User() {
		this.id = -1;
		this.usuario = "";
		this.senha = "";
		this.nome = "";
		this.email = "";
		this.setDescricao("");
		this.desejos = new ArrayList<Integer>();
		this.dataNascimento = LocalDate.now();
		this.gerenciador = false;
	}
	
	public User(int id, String usuario, String senha, String nome, String email, String descricao,Integer[] desejos,  LocalDate dataNascimento, boolean gerenciador) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		
		for(int i = 0; i < desejos.length; i++) 
		{
			this.desejos.add(desejos[i]);
		}
		
		this.dataNascimento = dataNascimento;
		this.gerenciador = gerenciador;
	}
	
	public User(int id, String usuario, String senha, String nome, String email, String descricao,ArrayList<Integer> desejos,  LocalDate dataNascimento, boolean gerenciador) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.desejos = desejos;
		this.dataNascimento = dataNascimento;
		this.gerenciador = gerenciador;
	}
	
	public User(int id, String usuario, String senha, String nome, String email, String descricao,  LocalDate dataNascimento, boolean gerenciador) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		desejos = new ArrayList<Integer>();
		desejos.add(-1);
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
	
	public void addDesejo(Integer id) {
		this.desejos.add(id);
	}
	
	public void tiraDesejo(Integer id) {
		desejos.remove(id);
	}
	
	public ArrayList<Integer> getDesejos(){
		return this.desejos;
	}
	
	public Integer[] getDesejos2() {
		Integer[] wishes = (Integer[]) desejos.toArray();
		
		return wishes;
	}
	
	public void setDesejos(ArrayList<Integer> desejos) {
		this.desejos = desejos;
	}
	
	
}