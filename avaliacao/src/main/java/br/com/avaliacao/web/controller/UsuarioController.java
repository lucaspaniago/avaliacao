package br.com.avaliacao.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.avaliacao.model.entidade.Usuario;
import br.com.avaliacao.model.service.AvaliacaoService;
import br.com.avaliacao.model.service.ServiceException;
import br.com.avaliacao.model.service.UsuarioService;

@Controller
//@ManagedBean
public class UsuarioController {

	// Objeto será vinculado com a tela
	private Usuario usuario;
	
	private DualListModel<String> cities;

	// Ponto de injeção de dependência
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
//	@Autowired
//	private PickListA pickListA;
	
//	private PickListAvaliacoes pickListAvaliacoes;

	// Lista vinculada à tela
	private List<Usuario> usuarios;

	private boolean edicao;

	public UsuarioController() {
		usuario = new Usuario();
		List<String> citiesSource = new ArrayList<String>();
		List<String> citiesTarget = new ArrayList<String>();

		citiesSource.add("San Francisco");
		citiesSource.add("London");
		citiesSource.add("Paris");
		citiesSource.add("Istanbul");
		citiesSource.add("Berlin");
		citiesSource.add("Barcelona");
		citiesSource.add("Rome");
		citiesSource.add("San Fra1ncisco");
		citiesSource.add("London1");
		citiesSource.add("Paris1");
		citiesSource.add("Istan1bul");
		citiesSource.add("Berli1n");
		citiesSource.add("Barce1lona");
		citiesSource.add("Rome1");

		this.setCities(new DualListModel<String>(citiesSource, citiesTarget));
	}

	@PostConstruct
	public void init() {
		edicao = false;
		usuarios = usuarioService.buscarTodos();
		if(!(this.usuario.getId() == null)){
			this.usuario.setAvaliacoes(avaliacaoService.buscarAvaliacaoPeloAluno(this.usuario));
		}
	}

	// Método será chamado por um botão
	public void salvar() {
		try {
			//System.out.println("ENTREI AQUI E SALVEI");
			// Chamando o salvar do service
			usuarioService.salvar(usuario);

			MensagemUtil.mensagemInfo("Salvo com sucesso.");

			if (!edicao) {
				usuarios.add(usuario);
			}

			usuario = new Usuario();
			this.edicao = false;

			// Dados impressos pelo toString
			System.out.println(usuario);
		} catch (ServiceException serviceException) {
			MensagemUtil.mensagemErro(serviceException.getMessage());
		}

	}

	public void prepararInclusao() {
		this.edicao = false;
		usuario = new Usuario();
	}

	public void prepararEdicao(SelectEvent evento) {//(Usuario usuario)
		this.edicao = true;
		this.usuario = (Usuario) evento.getObject();//usuario;
	}
	
	public void editar (Usuario usuario) {
		this.edicao = true;
		this.usuario = usuario;
	}
	
	public void selecionar (Usuario usuario)throws IOException{
//		this.edicao = true;
		this.usuario = usuario;
		this.usuario.setAvaliacoes(avaliacaoService.buscarAvaliacaoPeloAluno(this.usuario));
		//this.pickListAvaliacoes = new PickListAvaliacoes(usuario);
		//this.pickListAvaliacoes.inicializaAvaliacoes(usuario);
		List<String> citiesSource = new ArrayList<String>();
		List<String> citiesTarget = new ArrayList<String>();

		citiesSource.add("San Francisco");
		citiesSource.add("London");
		citiesSource.add("Paris");
		citiesSource.add("Istanbul");
		citiesSource.add("Berlin");
		citiesSource.add("Barcelona");
		citiesSource.add("Rome");

		this.setCities(new DualListModel<String>(citiesSource, citiesTarget));
		
	}
	
	public void excluir(Usuario usuario) {
		try {
			usuarioService.excluir(usuario);

			MensagemUtil.mensagemInfo("Usuário excluído.");
			usuarios.remove(usuario);
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro("Erro ao excluir. Tente novamente.");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public List<Usuario> nomes(String letras) {
//		List<String> palavras = Arrays.asList("Fabio", "Fabio Oliverira",
//				"Debora", "Lucas", "Marcelo");
		List<Usuario> usuariosEncontrados = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			if (usuario.toString().contains(letras)) {
				usuariosEncontrados.add(usuario);
			}
		}
		return usuariosEncontrados;
	}

	public DualListModel<String> getCities() {
		return cities;
	}

	public void setCities(DualListModel<String> cities) {
		this.cities = cities;
	}

}
