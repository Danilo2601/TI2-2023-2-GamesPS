package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Array;
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
			Integer[] array = new Integer[user.getDesejos().size()];
			array = user.getDesejos().toArray(array);
			Array array2 = conexao.createArrayOf("INTEGER", array);
			String sql = "INSERT INTO users (usuario, senha, nome, email, descricao, dataNascimento, gerenciador, desejos) "
		               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conexao.prepareStatement(sql);
			
			st.setString(1,user.getUsuario());
			st.setString(2,user.getSenha());
			st.setString(3, user.getNome());
			st.setString(4,user.getEmail());
			st.setString(5, user.getDescricao());
			st.setDate(6, Date.valueOf(user.getDataNascimento()));
			st.setBoolean(7, user.getGerenciador());
			st.setArray(8, array2);
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
			Integer[] ar;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){ 
	        	Array a = rs.getArray("desejos");
	        	if(a == null) {ar = new Integer[1]; ar[0] = -1;}
	        	else ar = (Integer[]) a.getArray();
	        	 users = new User(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"), rs.getString("nome"), rs.getString("email"),rs.getString("descricao"),passaArray(ar), rs.getDate("dataNascimento").toLocalDate(), rs.getBoolean("gerenciador"));
	        st.close();
		}
	        } catch (Exception e) {
	        System.out.print("AAAAAAAAA");
			System.err.println(e.getMessage());
		}
		return users;
	}
	
	
	public User getPorNome(String usuario) 
	{
		User users = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users WHERE LOWER(usuario) = LOWER('"+usuario+"')";
			Integer[] ar;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){ 
	        	Array a = rs.getArray("desejos");
	        	if(a == null) {ar = new Integer[1]; ar[0] = -1;}
	        	else ar = (Integer[]) a.getArray();
	        	 users = new User(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"), rs.getString("nome"), rs.getString("email"),rs.getString("descricao"),passaArray(ar), rs.getDate("dataNascimento").toLocalDate(), rs.getBoolean("gerenciador"));
	        st.close();
		}
	        } catch (Exception e) {
	        System.out.print("AAAAAAAAA");
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
	        	Array a = rs.getArray("desejos");
	        	User p = new User(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"), rs.getString("nome"), rs.getString("email"),rs.getString("descricao"),passaArray((Integer[])a.getArray()), rs.getDate("dataNascimento").toLocalDate(), rs.getBoolean("gerenciador")); 
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
			Integer[] array = new Integer[user.getDesejos().size()];
			array = user.getDesejos().toArray(array);
			Array array2 = conexao.createArrayOf("INTEGER", array);
			String sql = "UPDATE users SET usuario = '" + user.getUsuario() + "', "
					   + "senha = '" + user.getSenha() + "', " 
					   + "nome = '" + user.getNome() + "',"
					   + "email ='"+user.getEmail()+"', " 
					   + "descricao = '"+user.getDescricao()+"', "
					   + "dataNascimento = ?, "
					   + "gerenciador = " +user.getGerenciador()+", "
					   + "desejos = ? " +
					   " WHERE id = " + user.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(1, Date.valueOf(user.getDataNascimento()));
			st.setArray(2, array2);
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
			String sql = "SELECT * FROM users WHERE usuario LIKE '" + usuario + "' AND senha LIKE '" + DAO.toMD5(senha) + "'";
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}
	
	private ArrayList<Integer> passaArray(Integer[] wishes) {
		ArrayList<Integer> resp = new ArrayList<Integer>();
		for(int i = 0; i < wishes.length; i ++) 
		{
			resp.add(wishes[i]);
		}
		return resp;
	}
	
	public boolean verificarGerenciador(String usuario, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users WHERE usuario = '" +usuario+ "' AND senha = '" +DAO.toMD5(senha)+ "'";
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