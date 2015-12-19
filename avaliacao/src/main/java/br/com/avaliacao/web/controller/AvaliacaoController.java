package br.com.avaliacao.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.avaliacao.model.entidade.Avaliacao;
import br.com.avaliacao.model.entidade.Usuario;
import br.com.avaliacao.model.service.AvaliacaoService;
import br.com.avaliacao.model.service.ServiceException;

@Controller
// @ManagedBean
public class AvaliacaoController {

	// Objeto será vinculado com a tela
	private Avaliacao avaliacao;
	
	// Ponto de injeção de dependência
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	// Lista vinculada à tela
	private List<Avaliacao> avaliacoes;

	private boolean edicao;
	private boolean temAluno;

	public AvaliacaoController() {
		this.avaliacao = new Avaliacao();

		this.avaliacao.getAluno().setId(-1);
		this.avaliacao.getAluno().setDataDeNascimento(new Date());
	}

	// REVER ESSE MÉTODO POIS SOBRECARREGARÁ O SISTEMA (TALVEZ CARREGAR AS
	// AVALIAÇÕES DE UM ALUNO SERIA IDEAL)
	@PostConstruct
	public void init() {
		edicao = false;
		avaliacoes = avaliacaoService.buscarTodas();
//		if (!avaliacao.getUsuario().getId().equals(null)){
//			avaliacao.setIdade(avaliacao.getUsuario());
//		}
	}
	
	public String nova(){
		this.avaliacao = new Avaliacao();
		this.temAluno = false;
		this.avaliacao.getAluno().setId(-1);
		this.avaliacao.getAluno().setDataDeNascimento(new Date());
		
		return "nova-avaliacao";
	}

	// Método será chamado por um botão
	public void salvar() {
		if (this.temAluno){
			try {
				// Chamando o salvar do service
				avaliacaoService.salvar(avaliacao);

				MensagemUtil.mensagemInfo("Salvo com sucesso.");

				if (!edicao) {
					avaliacoes.add(avaliacao);
				}

				//avaliacao = new Avaliacao();
				this.edicao = false;

				// Dados impressos pelo toString
				System.out.println(avaliacao);
				
//				return "confirma-avaliacao.xhtml";
			} catch (ServiceException serviceException) {
				MensagemUtil.mensagemErro(serviceException.getMessage());
//				return null;
			}

		}else{
			//System.out.println("entrei aqui porque é igual a -1");
			MensagemUtil.mensagemErro("Escolha um aluno antes de salvar");
		}
	}
	
	public void prepararInclusao(Usuario aluno) throws IOException {
		this.edicao = false;
		avaliacao = new Avaliacao(aluno);
		
		this.temAluno = true;
		
//		FacesContext.getCurrentInstance().getExternalContext().redirect("formavaliacao.xhtml");
		
		
	}

	public void prepararInclusao() {
		this.edicao = false;
		avaliacao = new Avaliacao();
		//avaliacao.setPerimetria(new Perimetria());
		//avaliacao.setComposicaoCorporal(new ComposicaoCorporal());
		//aluno = new Usuario();
		this.temAluno = false;
		this.avaliacao.getAluno().setId(-1);
		this.avaliacao.getAluno().setDataDeNascimento(new Date());
	}

	public void prepararEdicao(SelectEvent evento) {
		this.edicao = true;
		this.temAluno = true;
		this.avaliacao = (Avaliacao) evento.getObject();
	}

	public void editar(Avaliacao avaliacao) {
		this.edicao = true;
		this.temAluno = true;
		this.avaliacao = avaliacao;
	}

	public void excluir(Avaliacao avaliacao) {
		try {
			avaliacaoService.excluir(avaliacao);

			MensagemUtil.mensagemInfo("Avaliação excluída.");
			avaliacoes.remove(avaliacao);
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro("Erro ao excluir. Tente novamente.");
		}
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	
	public Usuario getAluno() {
		return this.avaliacao.getAluno();
	}
	

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public boolean isEdicao() {
		return edicao;
	}
	
	public boolean isTemAluno() {
		return temAluno;
	}
	
	public void setIdade(){
		if (temAluno){
			Date data = new Date();
			
			if (data.equals(this.avaliacao.getData()))
				this.avaliacao.setIdade(this.avaliacao.getAluno());
			else
				this.avaliacao.setIdade(this.avaliacao.getAluno(), this.avaliacao.getData());
		}
	}
		
//	public void setAluno(Usuario aluno) {
//		this.aluno = aluno;
//	}
	
	public void setAlunoDaAvaliacao(SelectEvent event) {
		//this.aluno = (Usuario)event.getObject();
		this.avaliacao.setAluno((Usuario)event.getObject());
		this.temAluno = true;
		this.setIdade();
	}
	
	public void setIcq (){
		this.avaliacao.getPerimetria().setIcq();
		if(this.avaliacao.getPerimetria().getCintura() > 0.0 && this.avaliacao.getPerimetria().getQuadril() > 0.0){
			addMessage("Ídice de Cintura e Quadril calculado.");
		}
	}
	
	public void setImc (){
		this.avaliacao.getComposicaoCorporal().setImc();
		System.out.println("Entrei nessa biroska: "+ this.avaliacao.getComposicaoCorporal().getImc());
		if(this.avaliacao.getComposicaoCorporal().getImc()>0.0 && this.avaliacao.getComposicaoCorporal().getImc()>0.0){
			addMessage("Índice de massa corporal calculado.");
		}
	}
	
	public void calcularMassaGordaEMagra (){
		this.avaliacao.getComposicaoCorporal().calculaMassaGordaEMagra();
		
		addMessage("Massa magra e massa gorda calculadas.");
	}
	
	public void calcularPercentualDeGordura (ActionEvent actionEvent) throws ServiceException{
		try {
			this.avaliacao.getComposicaoCorporal().setPercentualDeGordura(this.avaliacao.getComposicaoCorporal().calcularPercentualDeGordura());
			
			calcularMassaGordaEMagra();
			
			addMessage("Percentual de gordura calculado com Pollock 7 Dobras.");
		} catch (ServiceException e) {
			addMessage(e.getMessage());
		} 
	}
	
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
