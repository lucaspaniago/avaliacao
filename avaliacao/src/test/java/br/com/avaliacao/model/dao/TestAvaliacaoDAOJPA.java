package br.com.avaliacao.model.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.com.avaliacao.model.entidade.Avaliacao;
import br.com.avaliacao.model.entidade.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TestAvaliacaoDAOJPA {
	
	//Obtendo uma instancia de UsuarioService por meio de injecao de dependencia
	@Autowired
	private AvaliacaoDAO avaliacaoDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Test
	public void deve_salvar_uma_avaliacao() throws Exception {
		
		Usuario usuario = usuarioDAO.buscarPeloId(202);
		
		Avaliacao avaliacaoEsperada = new Avaliacao(usuario);
		
		avaliacaoDAO.salvar(avaliacaoEsperada);
		
		Avaliacao avaliacaoRetornada = avaliacaoDAO.buscarPeloId(avaliacaoEsperada.getId());
		assertEquals(avaliacaoEsperada , avaliacaoRetornada);
		//usuarioDAO.excluir(usuario);
		avaliacaoDAO.excluir(avaliacaoEsperada);
	}
	
//	@Test
//	public void deve_excluir_um_usuario() throws Exception {
//		Usuario usuarioParaExcluir = new Usuario("Pedro", "pedro", "senha123");
//		avaliacaoDAO.salvar(usuarioParaExcluir);
//		
//		avaliacaoDAO.excluir(usuarioParaExcluir);
//		
//		Usuario usuarioRetornado = avaliacaoDAO.buscarPeloId(usuarioParaExcluir.getId());
//		assertNull(usuarioRetornado.getId());
//	}
//	
//	@Test
//	public void deve_busca_pelo_id_um_usuario() throws Exception {
//		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
//		
//		avaliacaoDAO.salvar(usuarioEsperado);
//		
//		Usuario usuarioRetornado = avaliacaoDAO.buscarPeloId(usuarioEsperado.getId());
//		assertEquals(usuarioEsperado , usuarioRetornado);
//		avaliacaoDAO.excluir(usuarioEsperado);
//	}
//	
//	@Test
//	public void deve_busca_pelo_login_ou_nome_um_usuario() throws Exception {
//		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
//		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
//		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");
//
//		avaliacaoDAO.salvar(usuarioEsperado);
//		avaliacaoDAO.salvar(usuarioAuxiliar);
//		avaliacaoDAO.salvar(usuarioAuxiliar1);
//
//		List<Usuario> listaDeUsuariosRetornados = avaliacaoDAO.buscarPeloLoginOuNome(usuarioEsperado.getLogin());
//
//		Integer contador = 0;
//		System.out.println("BUSCA POR NOME OU LOGIN:\ntamanho do resultado: "
//				+ listaDeUsuariosRetornados.size());
//		for (Usuario u : listaDeUsuariosRetornados) {
//			contador++;
//			System.out.println("resultado " + contador + ": " + u.getLogin() + ", " + u.getNome() + ", " + u.getId() + ".");
//			if (usuarioEsperado.equals(u)) {
//				System.out.println("Achei um igual\n" + "Nome esperado: "
//						+ usuarioEsperado.getNome() + "\nATributos do u: "
//						+ u.getNome() + u.getLogin());
//				assertEquals(usuarioEsperado, u);
//			}
//		}
//
//		avaliacaoDAO.excluir(usuarioEsperado);
//		avaliacaoDAO.excluir(usuarioAuxiliar);
//		avaliacaoDAO.excluir(usuarioAuxiliar1);
//	}
//	
//	@Test
//	public void deve_busca_pelo_login_usuarios() throws Exception {
//		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
//		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
//		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");
//
//		avaliacaoDAO.salvar(usuarioEsperado);
//		avaliacaoDAO.salvar(usuarioAuxiliar);
//		avaliacaoDAO.salvar(usuarioAuxiliar1);
//
//		List<Usuario> listaDeUsuariosRetornados = avaliacaoDAO.buscarPeloLogin(usuarioEsperado.getLogin());
//
//		Integer contador = 0;
//		System.out.println("BUSCA POR LOGIN:\ntamanho do resultado: "
//				+ listaDeUsuariosRetornados.size());
//		for (Usuario u : listaDeUsuariosRetornados) {
//			contador++;
//			System.out.println("resultado " + contador + ": " + u.getLogin() + ", " + u.getNome() + ", " + u.getId() + ".");
//			if (usuarioEsperado.equals(u)) {
//				System.out.println("Achei um igual\n" + "Nome esperado: "
//						+ usuarioEsperado.getNome() + "\nATributos do u: "
//						+ u.getNome() + u.getLogin());
//				assertEquals(usuarioEsperado, u);
//			}
//		}
//
//		avaliacaoDAO.excluir(usuarioEsperado);
//		avaliacaoDAO.excluir(usuarioAuxiliar);
//		avaliacaoDAO.excluir(usuarioAuxiliar1);
//	}
//	
//	@Test
//	public void deve_busca_pelo_nome_usuarios() throws Exception {
//		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
//		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
//		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");
//
//		avaliacaoDAO.salvar(usuarioEsperado);
//		avaliacaoDAO.salvar(usuarioAuxiliar);
//		avaliacaoDAO.salvar(usuarioAuxiliar1);
//
//		List<Usuario> listaDeUsuariosRetornados = avaliacaoDAO.buscarPeloNome(usuarioEsperado.getLogin());
//
//		Integer contador = 0;
//		System.out.println("BUSCA POR NOME:\ntamanho do resultado: "
//				+ listaDeUsuariosRetornados.size());
//		for (Usuario u : listaDeUsuariosRetornados) {
//			contador++;
//			System.out.println("resultado " + contador + ": " + u.getLogin() + ", " + u.getNome() + ", " + u.getId() + ".");
//			if (usuarioEsperado.equals(u)) {
//				System.out.println("Achei um igual\n" + "Nome esperado: "
//						+ usuarioEsperado.getNome() + "\nATributos do u: "
//						+ u.getNome() + u.getLogin());
//				assertEquals(usuarioEsperado, u);
//			}
//		}
//
//		avaliacaoDAO.excluir(usuarioEsperado);
//		avaliacaoDAO.excluir(usuarioAuxiliar);
//		avaliacaoDAO.excluir(usuarioAuxiliar1);
//	}
//	
//	@Test
//	public void deve_busca_pelo_login_exato_um_usuario() throws Exception {
//		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
//
//		avaliacaoDAO.salvar(usuarioEsperado);
//
//		Usuario usuarioRetornado = avaliacaoDAO.buscarPeloLoginExato(usuarioEsperado.getLogin());
//
//		assertEquals(usuarioRetornado, usuarioEsperado);
//
//		avaliacaoDAO.excluir(usuarioEsperado);
//	}
//	
//	@Test
//	public void deve_alterar_um_usuario() throws Exception {
//		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
//		avaliacaoDAO.salvar(usuarioEsperado);
//		
//		Usuario usuarioRetornadoPeloId = avaliacaoDAO
//				.buscarPeloId(usuarioEsperado.getId());
//
//		String senhaAntes = usuarioRetornadoPeloId.getSenha();
//		String nomeAntes = usuarioRetornadoPeloId.getNome();
//		Integer idAntes = usuarioRetornadoPeloId.getId();
//
//		usuarioEsperado.setNome("Pedromodificado");
//		usuarioEsperado.setSenha("senha123Modificada");
//
//		avaliacaoDAO.alterar(usuarioEsperado);
//
//		usuarioRetornadoPeloId = avaliacaoDAO.buscarPeloId(usuarioEsperado.getId());
//
//		String senhaDepois = usuarioRetornadoPeloId.getSenha();
//		String nomeDepois = usuarioRetornadoPeloId.getNome();
//		Integer idDepois = usuarioRetornadoPeloId.getId();
//
//		Assert.assertTrue(idAntes.equals(idDepois)
//				&& !senhaAntes.equals(senhaDepois)
//				&& !nomeAntes.equals(nomeDepois));
//
//		avaliacaoDAO.excluir(usuarioEsperado);
//	}
//	
//	@Test
//	public void deve_buscar_todos_os_usuario() throws Exception {
//		Usuario usuario1 = new Usuario("Pedro", "pedro", "senha123");
//		Usuario usuario2 = new Usuario("Lucas", "lucas", "senha123");
//		avaliacaoDAO.salvar(usuario1);
//		avaliacaoDAO.salvar(usuario2);
//		
//		
//		List<Usuario> lista = avaliacaoDAO.buscarTodos();
//		
//		Assert.assertTrue(lista.size() == 2);
//		avaliacaoDAO.excluir(usuario1);
//		avaliacaoDAO.excluir(usuario2);
//	}
}
