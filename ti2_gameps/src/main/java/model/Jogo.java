package model;

public class Jogo {
	private int id;
	private String nome;
	private int[] categorias;
	private String empresa;
	private String descricao;
	private double avaliacao;
	private String[] links;
	private String especificacoes;
	
	
	
	public Jogo() {
		this.id = -1;
		this.nome = "";
		this.categorias[0] = -1;
		this.empresa = "";
		this.descricao = "";
		this.avaliacao = -1.0;
		this.links[0] = "";
		this.especificacoes = "";
	}
	
	public Jogo(int id, String nome, int[] categorias, String empresa, String descricao, double avaliacao, String[] links, String especificacoes) {
		this.id = id;
		this.nome = nome;
		this.categorias = categorias;
		this.empresa = empresa;
		this.descricao = descricao;
		this.avaliacao = avaliacao;
		this.links = links;
		this.especificacoes = especificacoes;
	}
	
	public int getID() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	@Override
	public String toString() {
		return "Jogo[id = "+id+", nome = "+nome+", categorias = "+categorias+", publisher = "+empresa+", descrição =  "+descricao+" ]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Jogo) obj).getID());
	}

	public int[] getCategorias() {
		return categorias;
	}

	public void setCategorias(int[] categorias) {
		this.categorias = categorias;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String[] getLinks() {
		return links;
	}

	public void setLinks(String[] links) {
		this.links = links;
	}

	public String getEspecificacoes() {
		return especificacoes;
	}

	public void setEspecificacoes(String especificacoes) {
		this.especificacoes = especificacoes;
	}	
	
}
