package br.com.avaliacao.model.dao;

import java.util.List;

import br.com.avaliacao.model.entidade.Avaliacao;
import br.com.avaliacao.model.entidade.Usuario;

public interface AvaliacaoDAO {
	void salvar (Avaliacao avaliacao) throws DAOException;
	List<Avaliacao> buscarTodas();
	void excluir (Avaliacao avaliacao) throws DAOException;
	Avaliacao buscarPeloId(Integer id) throws DAOException;
	void alterar(Avaliacao avaliacao) throws DAOException;
	List<Avaliacao> buscarAvaliacaoPeloAluno(Usuario aluno);
}
