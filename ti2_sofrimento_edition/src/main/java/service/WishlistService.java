package service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.UserDAO;
import model.User;
import spark.Request;
import spark.Response;
import spark.Session;

public class WishlistService {
	UserDAO userDAO = new UserDAO();
	
	public Object inserir(Request request,Response response) 
	{
		Path resourcePath = Paths.get("src", "main", "resources", "public");
        
	    Path filePath = resourcePath.resolve("desejo.html"); 
	    String form = "";
	    String nomeArquivo = ""+filePath;

		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String resp = "";
		
		int id = Integer.parseInt(request.params(":id"));
		
		
		Session session = request.session();
		
		if(session.attribute("key") != null) {
			try {
			String username = session.attribute("usuario");
			User useratual = userDAO.getPorNome(username);
			
			ArrayList<Integer> a = useratual.getDesejos();
			

			if(a.contains(id)) 
			{
				resp+="<script>\n"
							+ "alert(\"Jogo retirado\")\n"
							+ "</script>";
				useratual.tiraDesejo(id);
			}
			else
			{
				resp+="<script>\n"
						+ "alert(\"Jogo inserido\")\n"
						+ "</script>";
				useratual.addDesejo(id);
			}
			userDAO.update(useratual);
			}catch(Exception e) 
			{
				System.out.print("BBBBBBBBBBBB");
			}
		}else {
			resp+="<script>\n"
					+ "alert(\"Precisa logar\")\n"
					+ "</script>";
		}
		
		resp += "<meta http-equiv=\"refresh\" content=1;url=\"/detalhes/"+id+"\">";
		
		form = form.replaceFirst("<SUBSTITUA>", resp);
		return resp;
	}
}
