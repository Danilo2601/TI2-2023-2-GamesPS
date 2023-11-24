package service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.JogoDAO;
import DAO.UserDAO;
import model.Jogo;
import model.User;
import spark.Request;
import spark.Response;
import spark.Session;

public class UsuarioService {
	
	public Object carregaPag(Request request, Response response) 
	{
		String form = "";
		String resp = "";
		String wlist = "";
		PaginaService paginaService = new PaginaService();
		UserDAO userDAO = new UserDAO();
		JogoDAO jogoDAO = new JogoDAO();
		User user;
		Jogo tmp;
		
		Path resourcePath = Paths.get("src", "main", "resources", "public");
        
	    Path filePath = resourcePath.resolve("usuario.html"); 
	    
	    String nomeArquivo = ""+filePath;
	    Session session = request.session();
	    
	    try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
	    
	    if(session.attribute("key") != null) {
			boolean gerenciador = session.attribute("gerenciador");
			String usuario = session.attribute("usuario");
			resp = paginaService.loadHeader(gerenciador, usuario);
			user = userDAO.getPorNome(usuario);
			ArrayList<Integer> wishlist = user.getDesejos();
			if(wishlist.size() > 1) 
			{
				wlist += "<div>\r\n"
						+ "							<span style=\"color: #FFFFFF; margin-left: 5px; margin-top: 8px;\">\r\n"
						+ "								LISTA DE DESEJOS\r\n"
						+ "							</span>\r\n"
						+ "						</div>\r\n"
						+ "                        \r\n"
						+ "                        <div class=\"wish\">";
				for(Integer a : wishlist) 
				{
					if(a != -1) 
					{
						tmp = jogoDAO.get(a);
						wlist += "<div class=\"cardio\">\r\n"
								+ "        <a class=\"barra-azul\" href=\"/jogagens.html#"+tmp.getId()+"\" >\r\n"
								+ "          </a><h3><a class=\"barra-azul\" href=\"/jogagens.html#"+tmp.getId()+"\"> <img class=\"fotini\" src=\""+tmp.getImagens()[0]+"\" <=\"\" h3=\"\">\r\n"
								+ "          </a>\r\n"
								+ "        </h3></div>";
					}
				}
				wlist += "</div>";
			}
		}else {
			resp = paginaService.loadHeader(false, "Account");
		}
	    
	    
		
		form = form.replaceFirst("<HEADER>", resp);
		form = form.replace("<WISHLIST>", wlist);
		
		
		return form;
	}
}
