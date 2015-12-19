package br.com.avaliacao.model.dao;

import java.util.List;

import br.com.avaliacao.model.entidade.Perfil;

public interface PerfilDAO {
	void salvar (Perfil perfil) throws DAOException;
	List<Perfil> buscarTodos();
	void excluir(Perfil usuario) throws DAOException;
	Perfil buscarPeloId(Integer id) throws DAOException;
}
