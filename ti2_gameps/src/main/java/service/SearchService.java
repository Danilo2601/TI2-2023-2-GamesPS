package service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import DAO.JogoDAO;
import model.Jogo;
import spark.Request;
import spark.Response;
import spark.Session;

public class SearchService {
	private JogoDAO jogoDAO = new JogoDAO();
	private PaginaService paginaService = new PaginaService();
	private String form;
	
	
	public Object pesquisa(Request request, Response response) {
		
		Path resourcePath = Paths.get("src", "main", "resources", "public");
		
		Session session = request.session();
		if(session.attribute("key") != null) {
			boolean gerenciador = session.attribute("gerenciador");
			String usuario = session.attribute("usuario");
			paginaService.loadHeader(gerenciador, usuario);
		}else {
			paginaService.loadHeader(false, "Account");
		}

	        
	    Path filePath = resourcePath.resolve("pesquisa.html"); 

	    String alvo = request.queryParams("searcher");
	    
	    
		
		String nomeArquivo = ""+filePath;
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		

		
		String pegaJogo = "";
		List<Jogo> jogos = jogoDAO.getSearch(alvo);
		
		for(Jogo p : jogos) 
		{
			
			pegaJogo += "<a class=\"game-card\">\n";
			pegaJogo += "<img src=\""+ p.getImagens()[0] +"\">";
			pegaJogo += "<h3>"+ p.getNome() +"</h3>";
			pegaJogo += "</a>\r\n";

		}
		
		
		form = form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", pegaJogo);
		
		return form;
	}
}
