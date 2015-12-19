package br.com.avaliacao.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import br.com.avaliacao.model.entidade.Avaliacao;
import br.com.avaliacao.model.entidade.Usuario;

@Repository
public class AvaliacaoDAOJPA implements AvaliacaoDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NESTED)
	public void salvar(Avaliacao avaliacao) throws DAOException {
		// EntityTransaction transacao = entityManager.getTransaction();
		// transacao.begin();
		// entityManager.merge(usuario);
		// transacao.commit();

		try {
			entityManager.persist(avaliacao);
		} catch (Exception e) {
			throw new DAOException("N�o foi poss�vel salvar.", e);
		}
	}

	@Override @SuppressWarnings("unchecked")
	public List<Avaliacao> buscarTodas(){
		return entityManager.createQuery("FROM Avaliacao").getResultList();
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void excluir(Avaliacao avaliacao) throws DAOException {
		try {
			entityManager.remove(entityManager.getReference(Avaliacao.class,
					avaliacao.getId()));
		} catch (Exception e) {
			throw new DAOException("N�o foi poss�vel excluir.", e);
		}
	}

	@Override
	public Avaliacao buscarPeloId(Integer id) {
		Avaliacao avaliacaoRetorno = (Avaliacao) entityManager
				.find(Avaliacao.class, id);

		if (avaliacaoRetorno != null) {
			return avaliacaoRetorno;
		} else {
			return new Avaliacao();
		}
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void alterar(Avaliacao avaliacao) throws DAOException {
		try {
			entityManager.merge(avaliacao);
		} catch (Exception e) {
			throw new DAOException("N�o foi poss�vel salvar.", e);
		}
	}

	@Override @SuppressWarnings("unchecked")
	public List<Avaliacao> buscarAvaliacaoPeloAluno(Usuario aluno) {
		String jpql = "SELECT a FROM Avaliacao a WHERE a.aluno = :alunoIdParametro";
		Query consulta = entityManager.createQuery(jpql);
		consulta.setParameter("alunoIdParametro", aluno);
		
		return (List<Avaliacao>) consulta.getResultList();
	}

}
