package DAO;

import model.Jogo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class JogoDAO extends DAO {	
	public JogoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Jogo jogo) {
		boolean status = false;
		try {
			
			Integer[] categ = new Integer[jogo.getCategorias().size()];
			categ = jogo.getCategorias().toArray(categ);
			Array array = conexao.createArrayOf("INTEGER", categ);
			
			String sql = "INSERT INTO jogo (nome, categorias, empresa, descricao, avaliacao, links, especificacoes, imagens) "
		            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st = conexao.prepareStatement(sql);
		st.setString(1, jogo.getNome());
		st.setArray(2, array);
		st.setString(3, jogo.getEmpresa());
		st.setString(4, jogo.getDescricao());
		st.setDouble(5, jogo.getAvaliacao());
		st.setArray(6, conexao.createArrayOf("varchar", jogo.getLinks()));
		st.setString(7, jogo.getEspecificacoes());
		st.setArray(8, conexao.createArrayOf("varchar", jogo.getImagens()));

		st.executeUpdate();
		st.close();
		status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Jogo get(int id) {
		Jogo jogo = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){
	        	Array a = rs.getArray("categorias");
	        	Array b = rs.getArray("links");
	        	Array c = rs.getArray("imagens");
	        	
	        	
	        	jogo = new Jogo(rs.getInt("id"), rs.getString("nome"), passaArray((Integer[])a.getArray()), 
     				   rs.getString("empresa"), 
     				   rs.getString("descricao"),
			               rs.getDouble("avaliacao"),
			               (String[])b.getArray(),
			               rs.getString("especificacoes"),(String[])c.getArray());
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogo;
	}
	
	
	public List<Jogo> get() {
		return get("id");
	}

	
	public List<Jogo> getOrderById() {
		return get("id");		
	}
	
	
	public List<Jogo> getOrderByNome() {
		return get("nome");		
	}
	
	private List<Jogo> get(String orderBy) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Array a = rs.getArray("categorias");
	        	Array b = rs.getArray("links");
	        	Array c = rs.getArray("imagens");
	        	
	        	
	        	
	        	
	        	Jogo p = new Jogo(rs.getInt("id"), rs.getString("nome"), passaArray((Integer[])a.getArray()), 
     				   rs.getString("empresa"), 
     				   rs.getString("descricao"),
			               rs.getDouble("avaliacao"),
			               (String[])b.getArray(),
			               rs.getString("especificacoes"),(String[])c.getArray());
	            jogos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}
	
	public List<Jogo> getSearch(String alvo) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo WHERE nome LIKE '%" + alvo + "%'" ;
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Array a = rs.getArray("categorias");
	        	Array b = rs.getArray("links");
	        	Array c = rs.getArray("imagens");
	        	
	        	
	        	
	        	
	        	Jogo p = new Jogo(rs.getInt("id"), rs.getString("nome"), passaArray((Integer[])a.getArray()), 
     				   rs.getString("empresa"), 
     				   rs.getString("descricao"),
			               rs.getDouble("avaliacao"),
			               (String[])b.getArray(),
			               rs.getString("especificacoes"),(String[])c.getArray());
	            jogos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}
	
	
	public boolean update(Jogo jogo) {
		boolean status = false;
		try {
			
			Integer[] categ = new Integer[jogo.getCategorias().size()];
			categ = jogo.getCategorias().toArray(categ);
			Array array = conexao.createArrayOf("INTEGER", categ);
			
			
			String sql = "UPDATE jogo SET nome = '" + jogo.getNome() + "', "
					   + "categorias = ?, " 
					   + "empresa = ?, "
					   + "descricao = ?, " 
					   + "avaliacao = ?, " 
					   + "links = ?, "
					   + "especificacoes = ?, "
					   + "imagens = ? WHERE id = " + jogo.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			
			st.setArray(1, array);
			st.setString(2, jogo.getEmpresa());
			st.setString(3, jogo.getDescricao());
			st.setDouble(4, jogo.getAvaliacao());
			st.setArray(5, conexao.createArrayOf("varchar", jogo.getLinks()));
			st.setString(6, jogo.getEspecificacoes());
			st.setArray(7, conexao.createArrayOf("varchar", jogo.getImagens()));
			
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	private ArrayList<Integer> passaArray(Integer[] categ) {
		ArrayList<Integer> resp = new ArrayList<Integer>();
		for(int i = 0; i < categ.length; i ++) 
		{
			resp.add(categ[i]);
		}
		return resp;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM jogo WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}