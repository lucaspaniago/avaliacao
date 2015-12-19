	package br.com.avaliacao.web.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avaliacao.model.entidade.Usuario;
import br.com.avaliacao.model.service.ServiceException;
import br.com.avaliacao.model.service.UsuarioService;

@Component ("usuarioConverter")
public class UsuarioConverter implements Converter {
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String valor) {
		Integer id = null;
		try {
			id = Integer.parseInt(valor);
			return usuarioService.buscarPeloId(id);
		} catch (NumberFormatException | ServiceException e) {
			MensagemUtil.mensagemErro("Erro ao carregar usu�rio.");
		}
		return id;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object valor) {
		if(valor == null || !(valor instanceof Usuario)) {
			return "";
		}
		Usuario usuario = (Usuario) valor;
		return usuario.getId().toString();
	}

}
