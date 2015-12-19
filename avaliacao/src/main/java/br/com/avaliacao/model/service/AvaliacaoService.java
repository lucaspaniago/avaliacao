//parei AQUI!!!! DEVO REALIZAR OS TESTES TESTUSUARIOSERVICE
package br.com.avaliacao.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.avaliacao.model.dao.AvaliacaoDAO;
import br.com.avaliacao.model.dao.DAOException;
import br.com.avaliacao.model.entidade.Avaliacao;
import br.com.avaliacao.model.entidade.Usuario;

@Service
public class AvaliacaoService{

	// Inje�ao de dependencia
	@Autowired
	@Qualifier("avaliacaoDAOJPA")
	private AvaliacaoDAO avaliacaoDAO;
	
	public void salvar (Avaliacao avaliacao) throws ServiceException{
		try {
			if (avaliacao.getId() != null) {
				avaliacaoDAO.alterar(avaliacao);
			} else {
				avaliacaoDAO.salvar(avaliacao);
			}
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}
	
	public List<Avaliacao> buscarTodas(){
			return avaliacaoDAO.buscarTodas();
	}
	
	public void excluir(Avaliacao avaliacao) throws ServiceException {
		try {
				avaliacaoDAO.excluir(avaliacao);
		} catch(DAOException daoException) {
			throw new ServiceException(daoException);
		}
	}
	
	
	public Avaliacao buscarPeloId(Integer id) throws ServiceException {
		try {
			return avaliacaoDAO.buscarPeloId(id);
		} catch (DAOException daoException) {
			throw new ServiceException(daoException);
		}
		
	}
	
	public List<Avaliacao> buscarAvaliacaoPeloAluno(Usuario aluno){
		return avaliacaoDAO.buscarAvaliacaoPeloAluno(aluno);
	}
	
}
