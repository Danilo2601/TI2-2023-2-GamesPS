package service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.Scanner;

import DAO.JogoDAO;
import model.Jogo;
import spark.Request;
import spark.Response;
import spark.Session;

public class PaginaService {
	private JogoDAO jogoDAO = new JogoDAO();
	private String form;
	
	public Object preencher(Request request,Response response) {
		Path resourcePath = Paths.get("src", "main", "resources", "public");
        
	    Path filePath = resourcePath.resolve("pag_jogo.html"); 
	    
	    int id = Integer.parseInt(request.params(":id"));	
	    
	    Jogo jogo = jogoDAO.get(id);
	    
	    String header;
	    String estilar = "";
	    Session session = request.session();
		if(session.attribute("key") != null) {
			boolean gerenciador = session.attribute("gerenciador");
			String usuario = session.attribute("usuario");
			
			header = loadHeader(gerenciador, usuario);
			try {
			Array a = session.attribute("desejos");
			Integer[] ar = (Integer[])a.getArray();
			for(int i = 0; i < ar.length; i++) 
			{
				if(ar[i] == id) 
				{
					estilar += "#SAVE{"
							+ "color: yellow;\r\n"
							+ "	background-color: yellow;"
							+ "}";
					i = ar.length+1;
				}
			}
			}catch(Exception e) 
			{
				System.out.print(e);
			}
		}else {
			header = loadHeader(false, "Account");
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
				+ "                    <a id=\"butonas\" href=\"/wishing.html#"+jogo.getId()+"\">\n"
				+ "						<i class=\"fa fa-star\" id=\"SAVE\"></i>\n"
				+ "						</a>\r\n"
				+ "                    <div id=\"score\">"+jogo.getAvaliacao()+"</div>\r\n"
				+ "                </div>\r\n"
				+ "                <p id=\"sinopsys\">"+jogo.getDescricao()+"</p>\r\n"
				+ "            </div>";
		
		form = form.replaceFirst("<div id=\"info\" class=\"col\"></div>", pegarJogo3);
		form = form.replaceFirst("SUBSTITUA-ISSO", estilar);
		
		return form;
	}

	public String loadHeader(boolean gerenciador, String usuario){
		
		String header = "";
		
		if(gerenciador){
			header += "<header id = \"header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" class=\"bi bi-list\" viewBox=\"0 0 16 16\">"
			+"					<path fill-rule=\"evenodd\" d=\"M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z\"/>"
			+"				</svg>"
			+"			</button>"
			+"			<div class=\"logo_header\">"
			+"				<a href=\"homeless.html\">"
			+"					<img src=\"/image/logo-ti2.png\" alt=\"Logo\">"
			+"				</a>"
			+"			</div>"
			+"			<div class=\"navigation_header\" id=\"navigation_header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\" id=\"btn2\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-x\" viewBox=\"0 0 16 16\">"
			+"					<path d=\"M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z\"/>"
			+"				</svg>"
			+"				</button>"
			+"				<a href=\"/crud_jogo.html\" >Jogos</a>"
			+"				<a href=\"/crudUser.html\">Usuários</a>"
			+"				<a href=\"/pesquisagen.html\">Search</a>"
			+"				<a href=\"/usuagens.html\">"+usuario+"</a>"
			+"			</div>"
			+"		</header>";

		}else{
			header += "<header id =\"header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" class=\"bi bi-list\" viewBox=\"0 0 16 16\">"
			+"					<path fill-rule=\"evenodd\" d=\"M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z\"/>"
			+"				</svg>"
			+"			</button>"
			+"			<div class=\"logo_header\">"
			+"				<a href=\"homeless.html\">"
			+"					<img src=\"/image/logo-ti2.png\" alt=\"Logo\">"
			+"				</a>"
			+"			</div>"
			+"			<div class=\"navigation_header\" id=\"navigation_header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\" id=\"btn2\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-x\" viewBox=\"0 0 16 16\">"
			+"					<path d=\"M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z\"/>"
			+"				</svg>"
			+"				</button>"
			+"				<a href=\"/pesquisagen.html\">Search</a>"
			+"				<a href=\"/usuagens.html\">"+usuario+"</a>"
			+"			</div>"
			+"		</header>";
		}


		return header;
	}
	
public String loadHeader(Request request, Response response) {
		
		Session session = request.session();
		
		boolean gerenciador = session.attribute("gerenciador");
		String usuario = session.attribute("usuario");
		
		String header = "";
		
		if(gerenciador){
			header += "<header id = \"header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" class=\"bi bi-list\" viewBox=\"0 0 16 16\">"
			+"					<path fill-rule=\"evenodd\" d=\"M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z\"/>"
			+"				</svg>"
			+"			</button>"
			+"			<div class=\"logo_header\">"
			+"				<a href=\"/homeless.html\">"
			+"					<img src=\"/image/logo-ti2.png\" alt=\"Logo\">"
			+"				</a>"
			+"			</div>"
			+"			<div class=\"navigation_header\" id=\"navigation_header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\" id=\"btn2\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-x\" viewBox=\"0 0 16 16\">"
			+"					<path d=\"M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z\"/>"
			+"				</svg>"
			+"				</button>"
			+"				<a href=\"/crud_jogo.html\" >Jogos</a>"
			+"				<a href=\"/crudUser.html\">Usuários</a>"
			+"				<a href=\"/pesquisagen.html\">Search</a>"
			+"				<a href=\"/usuagens.html\">"+usuario+"</a>"
			+"			</div>"
			+"		</header>";

		}else if(gerenciador == false && session.attribute("key") != null){
			header += "<header id =\"header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" class=\"bi bi-list\" viewBox=\"0 0 16 16\">"
			+"					<path fill-rule=\"evenodd\" d=\"M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z\"/>"
			+"				</svg>"
			+"			</button>"
			+"			<div class=\"logo_header\">"
			+"				<a href=\"/homeless.html\">"
			+"					<img src=\"/image/logo-ti2.png\" alt=\"Logo\">"
			+"				</a>"
			+"			</div>"
			+"			<div class=\"navigation_header\" id=\"navigation_header\">"
			+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\" id=\"btn2\">"
			+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-x\" viewBox=\"0 0 16 16\">"
			+"					<path d=\"M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z\"/>"
			+"				</svg>"
			+"				</button>"
			+"				<a href=\"/pesquisagen.html\">Search</a>"
			+"				<a href=\"/usuagens.html\">"+usuario+"</a>"
			+"			</div>"
			+"		</header>";
		}else {
			header += "<header id =\"header\">"
					+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\">"
					+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" class=\"bi bi-list\" viewBox=\"0 0 16 16\">"
					+"					<path fill-rule=\"evenodd\" d=\"M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z\"/>"
					+"				</svg>"
					+"			</button>"
					+"			<div class=\"logo_header\">"
					+"				<a href=\"/homeless.html\" onclick=preventDefault()>"
					+"					<img src=\"/image/logo-ti2.png\" alt=\"Logo\">"
					+"				</a>"
					+"			</div>"
					+"			<div class=\"navigation_header\" id=\"navigation_header\">"
					+"				<button onclick=\"toggleSidebar()\" class=\"btn_icon_header\" id=\"btn2\">"
					+"				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-x\" viewBox=\"0 0 16 16\">"
					+"					<path d=\"M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z\"/>"
					+"				</svg>"
					+"				</button>"
					+"				<a href=\"/pesquisagens.html\">Search</a>"
					+"				<a href=\"/usuagens.html\">Account</a>"
					+"			</div>"
					+"		</header>";
		}


		return header;
		
	}
}