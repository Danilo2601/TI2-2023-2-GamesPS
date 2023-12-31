package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends DAO {	
	public UserDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(User user) {
		boolean status = false;
		try {
			String sql = "INSERT INTO users (usuario, senha, nome, email, descricao, dataNascimento, gerenciador) "
		               + "VALUES ('" + user.getUsuario() + "', '"
		               + user.getSenha() + "', '" + user.getNome() + "','"+ user.getEmail()+"', '"+user.getDescricao()+"', ?, "+user.getGerenciador()+");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(1, Date.valueOf(user.getDataNascimento()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public User get(int id) {
		User users = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 users = new User(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"), rs.getString("nome"), rs.getString("email"),rs.getString("descricao"), rs.getDate("dataNascimento").toLocalDate(), rs.getBoolean("gerenciador"));
	        st.close();
		}
	        } catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return users;
	}
	
	
	public List<User> get() {
		return get("");
	}

	
	public List<User> getOrderByID() {
		return get("id");		
	}
	
	
	public List<User> getOrderByUser() {
		return get("usuario");		
	}
	
	
	public List<User> getOrderByDataNascimento() {
		return get("dataNascimento");		
	}
	
	
	private List<User> get(String orderBy) {
		List<User> userss = new ArrayList<User>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	User p = new User(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"), rs.getString("nome"), rs.getString("email"),rs.getString("descricao"), rs.getDate("dataNascimento").toLocalDate(), rs.getBoolean("gerenciador")); 
	        	userss.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return userss;
	}
	
	
	public boolean update(User user) {
		boolean status = false;
		try {  
			String sql = "UPDATE users SET usuario = '" + user.getUsuario() + "', "
					   + "senha = '" + user.getSenha() + "', " 
					   + "nome = '" + user.getNome() + "',"
					   + "email ='"+user.getEmail()+"', " 
					   + "descricao = '"+user.getDescricao()+"', "
					   + "dataNascimento = ?, "
					   + "gerenciador = " +user.getGerenciador()+
					   " WHERE id = " + user.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(1, Date.valueOf(user.getDataNascimento()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM users WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean autenticar(String usuario, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users WHERE usuario LIKE '" + usuario + "' AND senha LIKE '" + senha  + "'";
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}
	
	public boolean verificarGerenciador(String usuario, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users WHERE usuario LIKE'" +usuario+ "' AND senha LIKE '" +senha+ "'";
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 resp = rs.getBoolean("gerenciador");
	        st.close();
		}
	        } catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return resp;
	}
}