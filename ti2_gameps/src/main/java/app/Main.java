package app;

import static spark.Spark.*;

import service.JogoService;
import service.PaginaService;
import service.PrincipalService;
import service.SearchService;
import service.UserService;


public class Main {
	
	private static UserService userService = new UserService();
	private static JogoService jogoService = new JogoService();
	private static SearchService searchService = new SearchService();
	private static PrincipalService principalService = new PrincipalService();
	private static PaginaService paginaService = new PaginaService();
	
    public static void main(String[] args) {
        port(4567);
        
        staticFiles.location("/public");
        
        post("/user/insert", (request, response) -> userService.insert(request, response));

        get("/user/:id", (request, response) -> userService.get(request, response));
        
        get("/user/list/:orderby", (request, response) -> userService.getAll(request, response));

        get("/user/update/:id", (request, response) -> userService.getToUpdate(request, response));
        
        post("/user/update/:id", (request, response) -> userService.update(request, response));
           
        get("/user/delete/:id", (request, response) -> userService.delete(request, response));
        
        post("/gameps/cadastro", (request, response) -> userService.insertUser(request, response));
        
        post("/gameps/login", (request,response) -> userService.login(request, response));
        
        get("/gameps/logout", (request,response) -> userService.logout(request, response));
        
        post("/jogo/insert", (request, response) -> jogoService.insert(request, response));

        get("/jogo/:id", (request, response) -> jogoService.get(request, response));
        
        get("/jogo/list/:orderby", (request, response) -> jogoService.getAll(request, response));

        get("/jogo/update/:id", (request, response) -> jogoService.getToUpdate(request, response));
        
        post("/jogo/update/:id", (request, response) -> jogoService.update(request, response));
           
        get("/jogo/delete/:id", (request, response) -> jogoService.delete(request, response));
        
        get("/pesquisa/*", (request, response) -> searchService.pesquisa(request, response));
        
        get("/home/*",(request,response) -> principalService.criaPag(request, response));
        
        get("/detalhes/:id",(request,response) -> paginaService.preencher(request, response));
    }
}