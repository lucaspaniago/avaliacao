package br.com.avaliacao.model.dao;

import java.util.List;

import br.com.avaliacao.model.entidade.Usuario;

public interface UsuarioDAO {
	void salvar (Usuario usuario) throws DAOException;
	List<Usuario> buscarTodos();
	void excluir (Usuario usuario) throws DAOException;
	Usuario buscarPeloId(Integer id) throws DAOException;
	void alterar(Usuario usuario) throws DAOException;
	List<Usuario> buscarPeloNome(String nome);
	List<Usuario> buscarPeloLogin(String login);
	List<Usuario> buscarPeloLoginOuNome(String texto);
	Usuario buscarPeloLoginExato(String login);
}
