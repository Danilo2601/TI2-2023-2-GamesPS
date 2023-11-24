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

public class PrincipalService {
	private JogoDAO jogoDAO = new JogoDAO();
	private PaginaService paginaService = new PaginaService();
	private String form;
	
	public Object criaPag(Request request,Response response) {
		Path resourcePath = Paths.get("src", "main", "resources", "public");
        
	    Path filePath = resourcePath.resolve("principal.html"); 
	    
	    List<Jogo> jogos = jogoDAO.get();
	    
	    String header;
	    Session session = request.session();
		if(session.attribute("key") != null) {
			boolean gerenciador = session.attribute("gerenciador");
			String usuario = session.attribute("usuario");
			header = paginaService.loadHeader(gerenciador, usuario);
		}else {
			header = paginaService.loadHeader(false, "Account");
		}
		
	    String nomeArquivo = ""+filePath;
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		form = form.replaceFirst("<HEADER>", header);
		
		String pegaJogo1 = "";
		
		pegaJogo1 += "<div id=\"carouselExample\" class=\"carousel slide mb-5\" id=\"carroca\">\r\n"
				+ "  <div class=\"carousel-inner\">\r\n"
				+ "    <div class=\"carousel-item active\">\r\n"
				+ "      <img src=\""+jogos.get(0).getImagens()[0]+"\" class=\"d-block img-fluid\" alt=\"...\">\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"carousel-item\">\r\n"
				+ "      <img src=\""+jogos.get(3).getImagens()[0]+"\" class=\"d-block img-fluid\" alt=\"...\">\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"carousel-item\">\r\n"
				+ "      <img src=\""+jogos.get(5).getImagens()[0]+"\" class=\"d-block img-fluid\" alt=\"...\">\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "  <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExample\" data-bs-slide=\"prev\">\r\n"
				+ "    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\r\n"
				+ "    <span class=\"visually-hidden\">Previous</span>\r\n"
				+ "  </button>\r\n"
				+ "  <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExample\" data-bs-slide=\"next\">\r\n"
				+ "    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\r\n"
				+ "    <span class=\"visually-hidden\">Next</span>\r\n"
				+ "  </button>\r\n"
				+ "</div>";
		
		
		form = form.replaceFirst("<div class=\"slider\">slode</div>", pegaJogo1);
		
		String pegaJogo2 = "<div class=\"row\" id=\"conteiner-posts\">\n";
		
		for(Jogo a : jogos) {
			pegaJogo2 += "<div class=\"cardio\">\r\n"
					+ "        <a class=\"barra-azul\" href=\"/jogagens.html#"+a.getId()+"\" >\r\n"
					+ "          </a><h3><a class=\"barra-azul\" href=\"/jogagens.html#"+a.getId()+"\"> <img class=\"logo\" src=\""+a.getImagens()[0]+"\" <=\"\" h3=\"\">\r\n"
					+ "          </a>\r\n"
					+ "        </h3></div>";
		}
		
		pegaJogo2 += "</div>";
		
		form = form.replaceFirst("<div class=\"row\" id=\"conteiner-posts\"></div>", pegaJogo2);
		
		return form;
	}
}
