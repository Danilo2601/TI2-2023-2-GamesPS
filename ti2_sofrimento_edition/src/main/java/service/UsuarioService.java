package service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import spark.Request;
import spark.Response;
import spark.Session;

public class UsuarioService {
	
	public Object carregaPag(Request request, Response response) 
	{
		String form = "";
		String resp = "";
		PaginaService paginaService = new PaginaService();
		
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
		}else {
			resp = paginaService.loadHeader(false, "Account");
		}
		
		form = form.replaceFirst("<HEADER>", resp);
		
		return form;
	}
}
