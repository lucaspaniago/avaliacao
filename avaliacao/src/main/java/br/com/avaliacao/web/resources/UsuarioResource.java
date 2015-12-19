package br.com.avaliacao.web.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.avaliacao.model.entidade.Usuario;
import br.com.avaliacao.model.service.UsuarioService;


@Path("/usuario")
public class UsuarioResource {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GET
	@Path("/todos")
	@Produces("application/json")
	public ArrayList<Usuario> getTodosOsUsuarios(){
		
		return (ArrayList<Usuario>) usuarioService.buscarTodos();
	}

}
