package br.com.avaliacao.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.com.avaliacao.model.entidade.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class TestUsuarioDAOJPA {
	
	//Obtendo uma instancia de UsuarioService por meio de injecao de dependencia
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Test
	public void deve_salvar_um_usuario() throws Exception {
////		// Carregando o contexto do SPRING
////		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
////				"file:src/main/webapp/WEB-INF/springbeans.xml");
////		
////		//Obtendo instancia de UsuarioDAO pelo Contexto do Spring
////		UsuarioDAO usuarioDAO = (UsuarioDAO) ctx.getBean("usuarioDAOJPA");
////		
//		//Criando uma nova instancia de usuario para ser salvo
//		Usuario usu = new Usuario("Lucas", "lucas", "senha123");
//		
//		//Salvando
//		usuarioDao.salvar(usu);
//		
////		ctx.close();
		
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		
		usuarioDAO.salvar(usuarioEsperado);
		
		Usuario usuarioRetornado = usuarioDAO.buscarPeloId(usuarioEsperado.getId());
		assertEquals(usuarioEsperado , usuarioRetornado);
		usuarioDAO.excluir(usuarioEsperado);
	}
	
	@Test
	public void deve_excluir_um_usuario() throws Exception {
		Usuario usuarioParaExcluir = new Usuario("Pedro", "pedro", "senha123");
		usuarioDAO.salvar(usuarioParaExcluir);
		
		usuarioDAO.excluir(usuarioParaExcluir);
		
		Usuario usuarioRetornado = usuarioDAO.buscarPeloId(usuarioParaExcluir.getId());
		assertNull(usuarioRetornado.getId());
	}
	
	@Test
	public void deve_busca_pelo_id_um_usuario() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		
		usuarioDAO.salvar(usuarioEsperado);
		
		Usuario usuarioRetornado = usuarioDAO.buscarPeloId(usuarioEsperado.getId());
		assertEquals(usuarioEsperado , usuarioRetornado);
		usuarioDAO.excluir(usuarioEsperado);
	}
	
	@Test
	public void deve_busca_pelo_login_ou_nome_um_usuario() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");

		usuarioDAO.salvar(usuarioEsperado);
		usuarioDAO.salvar(usuarioAuxiliar);
		usuarioDAO.salvar(usuarioAuxiliar1);

		List<Usuario> listaDeUsuariosRetornados = usuarioDAO.buscarPeloLoginOuNome(usuarioEsperado.getLogin());

		Integer contador = 0;
		System.out.println("BUSCA POR NOME OU LOGIN:\ntamanho do resultado: "
				+ listaDeUsuariosRetornados.size());
		for (Usuario u : listaDeUsuariosRetornados) {
			contador++;
			System.out.println("resultado " + contador + ": " + u.getLogin() + ", " + u.getNome() + ", " + u.getId() + ".");
			if (usuarioEsperado.equals(u)) {
				System.out.println("Achei um igual\n" + "Nome esperado: "
						+ usuarioEsperado.getNome() + "\nATributos do u: "
						+ u.getNome() + u.getLogin());
				assertEquals(usuarioEsperado, u);
			}
		}

		usuarioDAO.excluir(usuarioEsperado);
		usuarioDAO.excluir(usuarioAuxiliar);
		usuarioDAO.excluir(usuarioAuxiliar1);
	}
	
	@Test
	public void deve_busca_pelo_login_usuarios() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");

		usuarioDAO.salvar(usuarioEsperado);
		usuarioDAO.salvar(usuarioAuxiliar);
		usuarioDAO.salvar(usuarioAuxiliar1);

		List<Usuario> listaDeUsuariosRetornados = usuarioDAO.buscarPeloLogin(usuarioEsperado.getLogin());

		Integer contador = 0;
		System.out.println("BUSCA POR LOGIN:\ntamanho do resultado: "
				+ listaDeUsuariosRetornados.size());
		for (Usuario u : listaDeUsuariosRetornados) {
			contador++;
			System.out.println("resultado " + contador + ": " + u.getLogin() + ", " + u.getNome() + ", " + u.getId() + ".");
			if (usuarioEsperado.equals(u)) {
				System.out.println("Achei um igual\n" + "Nome esperado: "
						+ usuarioEsperado.getNome() + "\nATributos do u: "
						+ u.getNome() + u.getLogin());
				assertEquals(usuarioEsperado, u);
			}
		}

		usuarioDAO.excluir(usuarioEsperado);
		usuarioDAO.excluir(usuarioAuxiliar);
		usuarioDAO.excluir(usuarioAuxiliar1);
	}
	
	@Test
	public void deve_busca_pelo_nome_usuarios() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");

		usuarioDAO.salvar(usuarioEsperado);
		usuarioDAO.salvar(usuarioAuxiliar);
		usuarioDAO.salvar(usuarioAuxiliar1);

		List<Usuario> listaDeUsuariosRetornados = usuarioDAO.buscarPeloNome(usuarioEsperado.getLogin());

		Integer contador = 0;
		System.out.println("BUSCA POR NOME:\ntamanho do resultado: "
				+ listaDeUsuariosRetornados.size());
		for (Usuario u : listaDeUsuariosRetornados) {
			contador++;
			System.out.println("resultado " + contador + ": " + u.getLogin() + ", " + u.getNome() + ", " + u.getId() + ".");
			if (usuarioEsperado.equals(u)) {
				System.out.println("Achei um igual\n" + "Nome esperado: "
						+ usuarioEsperado.getNome() + "\nATributos do u: "
						+ u.getNome() + u.getLogin());
				assertEquals(usuarioEsperado, u);
			}
		}

		usuarioDAO.excluir(usuarioEsperado);
		usuarioDAO.excluir(usuarioAuxiliar);
		usuarioDAO.excluir(usuarioAuxiliar1);
	}
	
	@Test
	public void deve_busca_pelo_login_exato_um_usuario() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");

		usuarioDAO.salvar(usuarioEsperado);

		Usuario usuarioRetornado = usuarioDAO.buscarPeloLoginExato(usuarioEsperado.getLogin());

		assertEquals(usuarioRetornado, usuarioEsperado);

		usuarioDAO.excluir(usuarioEsperado);
	}
	
	@Test
	public void deve_alterar_um_usuario() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		usuarioDAO.salvar(usuarioEsperado);
		
		Usuario usuarioRetornadoPeloId = usuarioDAO
				.buscarPeloId(usuarioEsperado.getId());

		String senhaAntes = usuarioRetornadoPeloId.getSenha();
		String nomeAntes = usuarioRetornadoPeloId.getNome();
		Integer idAntes = usuarioRetornadoPeloId.getId();

		usuarioEsperado.setNome("Pedromodificado");
		usuarioEsperado.setSenha("senha123Modificada");

		usuarioDAO.alterar(usuarioEsperado);

		usuarioRetornadoPeloId = usuarioDAO.buscarPeloId(usuarioEsperado.getId());

		String senhaDepois = usuarioRetornadoPeloId.getSenha();
		String nomeDepois = usuarioRetornadoPeloId.getNome();
		Integer idDepois = usuarioRetornadoPeloId.getId();

		Assert.assertTrue(idAntes.equals(idDepois)
				&& !senhaAntes.equals(senhaDepois)
				&& !nomeAntes.equals(nomeDepois));

		usuarioDAO.excluir(usuarioEsperado);
	}
	
	@Test
	public void deve_buscar_todos_os_usuario() throws Exception {
		Usuario usuario1 = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuario2 = new Usuario("Lucas", "lucas", "senha123");
		usuarioDAO.salvar(usuario1);
		usuarioDAO.salvar(usuario2);
		
		
		List<Usuario> lista = usuarioDAO.buscarTodos();
		
		Assert.assertTrue(lista.size() == 2);
		usuarioDAO.excluir(usuario1);
		usuarioDAO.excluir(usuario2);
	}
}
