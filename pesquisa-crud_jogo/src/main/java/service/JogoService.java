package service;


import java.io.File;
import java.util.List;
import java.util.Scanner;

import DAO.JogoDAO;
import model.Jogo;
import spark.Request;
import spark.Response;

public class JogoService {
	private JogoDAO jogoDAO = new JogoDAO();
	private String form;
	
	private final int FORM_INSERT = 1;
	private final int FORM_UPDATE = 2;
	private final int FORM_DETAIL = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	
	public JogoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Jogo(), FORM_ORDERBY_ID);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Jogo(), orderBy);
	}
	
	public void makeForm(int tipo, Jogo jogo,int orderby) {
		String nomeArquivo = "inserir.html";
		
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		String umJogo = "";
		if(tipo == FORM_INSERT) {
			String action = "/jogo/";
			action += "insert";
			umJogo += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umJogo += "<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">\r\n";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td colspan=\"3\" align=\"left\" id=\"title\"><font size=\"+2\"><b>INSERIR JOGOS</b></font></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td colspan=\"3\" align=\"left\"></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td>Nome: <input class=\"input--register\" type=\"text\" name=\"nome\"></td>\r\n";
			umJogo += "        <td>Categorias: <input class=\"input--register\" type=\"text\" name=\"categorias\" ></td>\r\n";
			umJogo += "        <td>Empresa: <input class=\"input--register\" type=\"text\" name=\"empresa\" ></td>\r\n";
			umJogo += "        <td>Descrição: <input class=\"input--register\" type=\"text\" name=\"descricao\"></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td>Avaliação: <input class=\"input--register\" type=\"text\" name=\"avaliacao\" ></td>\r\n";
			umJogo += "        <td>Links: <input class=\"input--register\" type=\"text\" name=\"links\"></td>\r\n";
			umJogo += "        <td>Especifições: <input class=\"input--register\" type=\"text\" name=\"especificacoes\"></td>\r\n";
			umJogo += "        <td>Imagens: <input class=\"input--register\" type=\"text\" name=\"imagens\"></td>\r\n";
			umJogo += "        <td align=\"center\"><input type=\"submit\" value=\"Enviar\" class= \"input--main__style input--button\"></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        </table>";
			umJogo += "		</form>\n";
		}
		else if(tipo == FORM_UPDATE) {
			String action = "/jogo/";
			action += "update";
			action += "/"+ jogo.getId();
			umJogo += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td colspan=\"3\" align=\"left\" id=\"title\"><font size=\"+2\"><b>UPDATE</b></font></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td colspan=\"3\" align=\"left\"></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td>Nome: <input class=\"input--register\" type=\"text\" name=\"nome\"></td>\r\n";
			umJogo += "        <td>Categorias: <input class=\"input--register\" type=\"text\" name=\"categorias\" ></td>\r\n";
			umJogo += "        <td>Empresa: <input class=\"input--register\" type=\"text\" name=\"empresa\" ></td>\r\n";
			umJogo += "        <td>Descrição: <input class=\"input--register\" type=\"text\" name=\"descricao\"></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        <tr>\r\n";
			umJogo += "        <td>Avaliação: <input class=\"input--register\" type=\"text\" name=\"avaliacao\" ></td>\r\n";
			umJogo += "        <td>Links: <input class=\"input--register\" type=\"text\" name=\"links\"></td>\r\n";
			umJogo += "        <td>Especifições: <input class=\"input--register\" type=\"text\" name=\"especificacoes\"></td>\r\n";
			umJogo += "        <td>Imagens: <input class=\"input--register\" type=\"text\" name=\"imagens\"></td>\r\n";
			umJogo += "        <td align=\"center\"><input type=\"submit\" value=\"Atualizar\" class= \"input--main__style input--button\"></td>\r\n";
			umJogo += "        </tr>\r\n";
			umJogo += "        </table>";
			umJogo += "		</form>\n";
			
		}
		else if(tipo == FORM_DETAIL) {
			
			umJogo += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umJogo += "\t\t<tr>";
			umJogo += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Jogo (ID " + jogo.getId() + ")</b></font></td>";
			umJogo += "\t\t</tr>";
			umJogo += "\t\t<tr>";
			umJogo += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umJogo += "\t\t</tr>";
			umJogo += "\t\t<tr>";
			umJogo += "\t\t\t<td>&nbsp;Nome: "+ jogo.getNome() +"</td>";
			umJogo += "\t\t\t<td>&nbsp;Descrição: "+ jogo.getDescricao() +"</td>";
			umJogo += "\t\t\t<td>Empresa: "+ jogo.getEmpresa() +"</td>";
			umJogo += "\t\t\t<td>Especificações: "+ jogo.getEspecificacoes() +"</td>";
			
			umJogo += "\t\t\t<td>Categorias: ";
			for(int i : jogo.getCategorias()) if(i != 0)umJogo += i + "  ";
			
			umJogo += "\t\t\t<td>Links: ";
			for(String i : jogo.getLinks()) umJogo += i + "  ";
			
			umJogo += "\t\t\t<td>Avaliação: "+ jogo.getAvaliacao() +"</td>";
			umJogo += "\t\t</tr>";
			umJogo += "\t</table>";		
			
		}
		
		form = form.replaceFirst("<p ADICIONE></p>", umJogo);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Jogos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/jogo/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/jogo/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Empresa</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Jogo> jogos;
		if (orderby == FORM_ORDERBY_ID) {                 	jogos = jogoDAO.getOrderById();
		} else if (orderby == FORM_ORDERBY_NOME) {		jogos = jogoDAO.getOrderByNome();
		} else {											jogos = jogoDAO.get();
		}
		
		int i = 0;
		String bgcolor = "";
		for (Jogo p : jogos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getId() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getEmpresa() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/jogo/" + p.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/jogo/update/" + p.getId() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteJogo('" + p.getId() + "', '" + p.getNome() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-PRODUTO>", list);	

	}
	
	public Object insert(Request request, Response response) {
		String descricao = request.queryParams("descricao");
    	String nome = request.queryParams("nome");
    	String[] categ = request.queryParams("categorias").split(" ");
    	String empresa = request.queryParams("empresa");
    	double avaliacao = Double.parseDouble(request.queryParams("avaliacao"));
    	String especificacoes = request.queryParams("especificacoes");
    	String[] links = request.queryParams("links").split(" ");
    	String[] imagens = request.queryParams("imagens").split(" ");
    	
    	int[] categorias = new int[5];
    	
    	for(int i = 0; i < categ.length; i++) {
    		categorias[i] = Integer.parseInt(categ[i]);
    	}
    	
		
		String resp = "";
		
		Jogo jogo = new Jogo(-1, nome,categorias,empresa,descricao,avaliacao,links,especificacoes,imagens);
		
		if(jogoDAO.insert(jogo) == true) {
            resp = "Jogo (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Jogo (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		return form.replaceFirst("<td><input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\"></td>", "<td>"+ resp +"</td>");
	}
	
	
	public Object get(Request request, Response response) {
		
		int id = Integer.parseInt(request.params(":id"));		
		Jogo jogo = (Jogo) jogoDAO.get(id);
		
		if (jogo != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, jogo, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Jogo " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Jogo jogo = (Jogo) jogoDAO.get(id);
		
		if (jogo != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, jogo, FORM_ORDERBY_ID);
        } else {
            response.status(404); // 404 Not found
            String resp = "Jogo " + id + " não encontrado.";
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
	
	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Jogo jogo = jogoDAO.get(id);
        String resp = "",categ = "";       

        if (jogo != null) {
        	jogo.setNome(request.queryParams("nome"));
        	categ = request.queryParams("categorias");        	
        	String[] conv = categ.split(" ");
        	int[] categorias = new int[5];        	
        	for(int i = 0; i < conv.length; i++) {categorias[i] = Integer.parseInt(conv[i]);}
        	jogo.setCategorias(categorias);
        	jogo.setEmpresa(request.queryParams("empresa"));
        	jogo.setAvaliacao(Double.parseDouble(request.queryParams("avaliacao")));
        	jogo.setDescricao(request.queryParams("descricao"));
        	jogo.setEspecificacoes(request.queryParams("especificacoes"));
        	jogo.setLinks(request.queryParamsValues("links"));
        	jogo.setImagens(request.queryParamsValues("imagens"));
        	jogoDAO.update(jogo);
        	response.status(200); // success
            resp = "Jogo (ID " + jogo.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogo (ID \" + jogo.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object delete(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
        Jogo jogo = jogoDAO.get(id);
        String resp = "";       

        if (jogo != null) {
            jogoDAO.delete(id);
            response.status(200); // success
            resp = "Jogo (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogo (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
