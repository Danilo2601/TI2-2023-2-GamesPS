package service;

import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import DAO.DAO;
import DAO.UserDAO;
import model.User;
import spark.Request;
import spark.Response;
import spark.Session;


public class UserService {

	private UserDAO userDAO = new UserDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_Id = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_DATA_NASCIMENTO= 3;
	
	
	public UserService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new User(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new User(), orderBy);
	}

	
	public void makeForm(int tipo, User user, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umUser = "";
		if(tipo != FORM_INSERT) {
			umUser += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/user/list/1\">Novo User</a></b></font></td>";
			umUser += "\t\t</tr>";
			umUser += "\t</table>";
			umUser += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/user/";
			String usuario, nome, senha, email, buttonLabel;
			LocalDate dataNascimento;
			boolean gerenciador;
			if (tipo == FORM_INSERT){
				action += "insert";
				usuario = "Inserir User";
				senha = "Inserir sua senha";
				nome = "Inserir seu nome";
				email = "Inserir e-mail";
				dataNascimento = LocalDate.now();
				gerenciador = false;
				buttonLabel = "Inserir";
			} else {
				action += "update/" + user.getId();
				usuario = "Atualizar User (Id " + user.getId() + ")";
				senha = user.getSenha();
				nome = user.getNome();
				email = user.getEmail();
				dataNascimento = user.getDataNascimento();
				gerenciador = user.getGerenciador();
				buttonLabel = "Atualizar";
			}
			umUser += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umUser += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + usuario + "</b></font></td>";
			umUser += "\t\t</tr>";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUser += "\t\t</tr>";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td>&nbsp;Usuário: <input class=\"input--register\" type=\"text\" name=\"usuario\" placeholder =\""+ usuario +"\"></td>";
			umUser += "\t\t\t<td>Senha: <input class=\"input--register\" type=\"password\" name=\"senha\" placeholder=\""+ senha +"\"></td>";
			umUser += "\t\t\t<td>E-mail: <input class=\"input--register\" type=\"email\" name=\"email\" placeholder=\""+ email +"\"></td>";
			umUser += "\t\t</tr>";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" placeholder=\""+ nome + "\"></td>";
			umUser += "\t\t\t<td>Data de Nascimento: <input class=\"input--register\" type=\"date\" name=\"dataNascimento\" placeholder=\""+ dataNascimento + "\"></td>";
			umUser += "\t\t\t<td>Gerenciador: <input class=\"input--register\" type=\"text\" name=\"gerenciador\" placeholder=\""+ gerenciador + "\"></td>";
			umUser += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umUser += "\t\t</tr>";
			umUser += "\t</table>";
			umUser += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umUser += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar User (Id " + user.getId() + ")</b></font></td>";
			umUser += "\t\t</tr>";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUser += "\t\t</tr>";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td>&nbsp;Usuário: "+ user.getUsuario() +"</td>";
			umUser += "\t\t\t<td>Senha: "+ user.getSenha() +"</td>";
			umUser += "\t\t\t<td>e-mail: "+ user.getEmail() +"</td>";
			umUser += "\t\t</tr>";
			umUser += "\t\t<tr>";
			umUser += "\t\t\t<td>&nbsp;Nome: "+ user.getNome() + "</td>";
			umUser += "\t\t\t<td>Idade: "+ user.getDataNascimento().toString() + "</td>";
			umUser += "\t\t\t<td>Gerenciador: "+ user.getGerenciador() + "</td>";
			umUser += "\t\t\t<td>&nbsp;</td>";
			umUser += "\t\t</tr>";
			umUser += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-USER>", umUser);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Users</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/user/list/" + FORM_ORDERBY_Id + "\"><b>Id</b></a></td>\n" +
        		"\t<td><a href=\"/user/list/" + FORM_ORDERBY_NOME + "\"><b>Usuario</b></a></td>\n" +
        		"\t<td><a href=\"/user/list/" + FORM_ORDERBY_DATA_NASCIMENTO + "\"><b>Idade</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<User> users;
		if (orderBy == FORM_ORDERBY_Id) {                 	users = userDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_NOME) {		users = userDAO.getOrderByUser();
		} else if (orderBy == FORM_ORDERBY_DATA_NASCIMENTO) {			users = userDAO.getOrderByDataNascimento();
		} else {											users = userDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		String color = "";
		for (User p : users) {
			bgcolor = (i++ % 2 == 0) ? "#09081C" : "#dddddd";
			color = (i % 2 == 0) ? "#09081C" : "#dddddd";
			list += "\n<tr bgcolor=\"" + bgcolor + "\">\n" +
					"\t<td style=\"color: " + color + ";\">" + p.getId() + "</td>\n" +
					"\t<td style=\"color: " + color + ";\">" + p.getUsuario() + "</td>\n" +
					"\t<td style=\"color: " + color + ";\">" + p.getDataNascimento().toString() + "</td>\n" +
					"\t<td align=\"center\" valign=\"middle\"><a href=\"/user/" + p.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
					"\t<td align=\"center\" valign=\"middle\"><a href=\"/user/update/" + p.getId() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
					"\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUser('" + p.getId() + "', '" + p.getUsuario() + "', '" + p.getNome() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
					"</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-USER>", list);				
	}
	
	public Object insert(Request request, Response response) throws Exception{
		String nome = request.queryParams("nome");
		String usuario = request.queryParams("usuario");
		String senha = request.queryParams("senha");
		String email = request.queryParams("email");
		String descricao = request.queryParams("descricao");
		LocalDate dataNascimento = LocalDate.parse(request.queryParams("dataNascimento"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		boolean gerenciador = Boolean.parseBoolean(request.params("gerenciador"));
		
		String resp = "";
		
		User user = new User(-1, usuario, DAO.toMD5(senha), nome, email, descricao, dataNascimento, gerenciador );
		
		if(userDAO.insert(user) == true) {
            resp = "User (" + usuario + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "User (" + usuario + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		User user = (User) userDAO.get(id);
		
		if (user != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, user, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "User " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		User user = (User) userDAO.get(id);
		
		if (user != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, user, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "User " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) throws Exception{
        int id = Integer.parseInt(request.params(":id"));
		User user = userDAO.get(id);
        String resp = "";       

        if (user != null) {
        	user.setUsuario(request.queryParams("usuario"));
        	user.setSenha(DAO.toMD5(request.queryParams("senha")));
        	user.setEmail(request.queryParams("email"));
        	user.setNome(request.queryParams("nome"));
        	user.setDataNascimento(LocalDate.parse(request.queryParams("dataNascimento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			user.setGerenciador(Boolean.parseBoolean(request.queryParams("gerenciador")));
        	userDAO.update(user);
        	response.status(200); // success
            resp = "User (Id " + user.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "User (Id \" + user.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        User user = userDAO.get(id);
        String resp = "";       

        if (user != null) {
            userDAO.delete(id);
            response.status(200); // success
            resp = "User (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "User (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object insertUser(Request request, Response response)throws Exception {
		String nome = request.queryParams("nome");
		String usuario = request.queryParams("usuario");
		String senha = request.queryParams("senha");
		String email = request.queryParams("email");
		String dataNascimentoParam = request.queryParams("dataNascimento");
		LocalDate dataNascimento;
		boolean gerenciador = false;
				
		if(dataNascimentoParam != null && !dataNascimentoParam.isEmpty()) {
			dataNascimento = LocalDate.parse(dataNascimentoParam, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}else {
			dataNascimento = LocalDate.now();
		}
		
		String resp = "";
		
		User user = new User(-1, usuario, DAO.toMD5(senha), nome, email, null, dataNascimento, gerenciador );
		
		if(userDAO.insert(user) == true) {
            resp = "Conta criada com sucesso!";
            response.status(201); // 201 Created
		} else {
			resp = "Falha ao criar conta!";
			response.status(400); // 404 Not found
		}
					
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
		
	public Object login(Request request, Response response) {
		String usuario = request.queryParams("usuario");
		String senha = request.queryParams("senha");
		boolean gerenciador = userDAO.verificarGerenciador(usuario,senha);
		String resp = "";
		
		
		if(userDAO.autenticar(usuario, senha)) {
			Session session = request.session(true);
			session.attribute("usuario", usuario);
			session.attribute("gerenciador", gerenciador);
			response.redirect("/index.html");
			resp = "Bem vindo(a)!";
		}else {
			response.redirect("/login.html");
			resp = "Usuario e/ou senha inválidos!";	
		}
		
		
		
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object logout(Request request, Response response) {
		Session session = request.session(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		response.redirect("/login.html");
		return "Deslogado com sucesso!";
	}
}