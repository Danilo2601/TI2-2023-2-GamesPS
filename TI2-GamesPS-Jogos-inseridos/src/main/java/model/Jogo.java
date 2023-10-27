package model;

import java.util.Arrays;

public class Jogo {
	private int id;
	private String nome;
	private int[] categorias;
	private String empresa;
	private String descricao;
	private double avaliacao;
	private String[] links;
	private String especificacoes;
	private String[] imagens;
	
	
	
	public Jogo() {
		this.id = -1;
		this.nome = "";
		this.categorias = new int[5];
		this.empresa = "";
		this.descricao = "";
		this.avaliacao = -1.0;
		this.links = new String[5];
		this.especificacoes = "";
		this.setImagens(new String[5]);
	}
	
	public Jogo(int id, String nome, int[] categorias, String empresa, String descricao, double avaliacao, String[] links, String especificacoes,String[] imagens) {
		this.id = id;
		this.nome = nome;
		this.categorias = categorias;
		this.empresa = empresa;
		this.descricao = descricao;
		this.avaliacao = avaliacao;
		this.links = links;
		this.especificacoes = especificacoes;
		this.imagens = imagens;
	}
	
	public Jogo(int id, String nome, Integer[] categorias, String empresa, String descricao, double avaliacao, String[] links, String especificacoes,String[] imagens) {
		this.id = id;
		this.nome = nome;
		
		int[] categ = Arrays.stream(categorias).mapToInt(Integer::intValue).toArray();
		
		this.categorias = categ;
		this.empresa = empresa;
		this.descricao = descricao;
		this.avaliacao = avaliacao;
		this.links = links;
		this.especificacoes = especificacoes;
		this.imagens = imagens;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Jogo) obj).getId());
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

	public String[] getImagens() {
		return imagens;
	}

	public void setImagens(String[] imagens) {
		this.imagens = imagens;
	}	
	
}
