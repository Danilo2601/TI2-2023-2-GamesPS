package service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import DAO.JogoDAO;
import model.Jogo;
import spark.Request;
import spark.Response;

public class PaginaService {
	private JogoDAO jogoDAO = new JogoDAO();
	private String form;
	
	public Object preencher(Request request,Response response) {
		Path resourcePath = Paths.get("src", "main", "resources", "public");
        
	    Path filePath = resourcePath.resolve("pag_jogo.html"); 
	    
	    int id = Integer.parseInt(request.params(":id"));	
	    
	    Jogo jogo = jogoDAO.get(id);

	    String nomeArquivo = ""+filePath;
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String pegarJogo = "<div class=\"carousel-inner \">";
		
		pegarJogo += "<div class=\"carousel-item active\"><img src=\""+jogo.getImagens()[0]+"\" class=\"d-block w-100\"alt=\"...\" /></div>";
		for (int i = 0; i < jogo.getImagens().length; i++) {
	        pegarJogo += "<div class=\"carousel-item\"><img src=\""+jogo.getImagens()[i]+"\" class=\"d-block w-100\"alt=\"...\" /></div>";
	    }
		
		pegarJogo += "</div>";
		
		form = form.replaceFirst("<div class=\"carousel-inner \"></div>", pegarJogo);
		
		String pegarJogo2 = "<div class=\"carousel-indicators\">";
		
		pegarJogo2 += "<button type=\"button\" data-bs-target=\"#carouselExampleIndicators\" data-bs-slide-to=\"0\" class=\"active 0 previw b S\" aria-current=\"true\" aria-label=\"Slide 1\"> <img class=\"d-block\"src=\""+jogo.getImagens()[0]+"\"class=\"img-fluid\" />";
		
		for (int i = 0; i < jogo.getImagens().length; i++) {
	        pegarJogo += "<button type=\"button\" data-bs-target=\"#carouselExampleIndicators\" data-bs-slide-to=\""+(i+1)+"\" aria-label=\"Slide "+(i+2)+"\" class=\"previw "+i+" b\"> <img class=\"d-block\"src=\""+jogo.getImagens()[i]+"\"class=\"img-fluid\" />";
	    }
		
		pegarJogo2 += "</div>";
		
		form = form.replaceFirst("<div class=\"carousel-indicators\"></div>", pegarJogo2);
		
		String pegarJogo3 = "<div id=\"info\" class=\"col\">\r\n"
				+ "                <div id=\"comp\">\r\n"
				+ "                    <h4 class=\"W\">By:"+jogo.getEmpresa()+"</h4>\r\n"
				+ "                    <h4 id=\"name\">"+jogo.getNome()+"</h4>\r\n"
				+ "                    <button class=\"fas fa-star\"id=\"SAVE\"></button>\r\n"
				+ "                    <div id=\"score\">"+jogo.getAvaliacao()+"</div>\r\n"
				+ "                </div>\r\n"
				+ "                <p id=\"sinopsys\">"+jogo.getDescricao()+"</p>\r\n"
				+ "            </div>";
		
		form = form.replaceFirst("<div id=\"info\" class=\"col\"></div>", pegarJogo3);
		
		return form;
	}
}
