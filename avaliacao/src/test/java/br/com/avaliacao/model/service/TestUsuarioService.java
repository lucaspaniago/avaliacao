package br.com.avaliacao.model.service;

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
public class TestUsuarioService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	public void testSalvar() throws ServiceException{
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		
		usuarioService.salvar(usuarioEsperado);
		
		Usuario usuarioRetornado = usuarioService.buscarPeloId(usuarioEsperado.getId());
		assertEquals(usuarioEsperado , usuarioRetornado);
		usuarioService.excluir(usuarioEsperado);
	}
	
	@Test
	public void buscarTodos() throws Exception {
		Usuario usuario1 = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuario2 = new Usuario("Lucas", "lucas", "senha123");
		usuarioService.salvar(usuario1);
		usuarioService.salvar(usuario2);
		
		
		List<Usuario> lista = usuarioService.buscarTodos();
		
		Assert.assertTrue(lista.size() == 2);
		usuarioService.excluir(usuario1);
		usuarioService.excluir(usuario2);
	}
	
	@Test
	public void excluir() throws Exception {
		Usuario usuarioParaExcluir = new Usuario("Pedro", "pedro", "senha123");
		usuarioService.salvar(usuarioParaExcluir);
		
		usuarioService.excluir(usuarioParaExcluir);
		
		Usuario usuarioRetornado = usuarioService.buscarPeloId(usuarioParaExcluir.getId());
		assertNull(usuarioRetornado.getId());
	}
	
	@Test
	public void buscarPeloId() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		
		usuarioService.salvar(usuarioEsperado);
		
		Usuario usuarioRetornado = usuarioService.buscarPeloId(usuarioEsperado.getId());
		assertEquals(usuarioEsperado , usuarioRetornado);
		usuarioService.excluir(usuarioEsperado);
	}
	
	@Test
	public void buscarPeloLoginOuNome() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");

		usuarioService.salvar(usuarioEsperado);
		usuarioService.salvar(usuarioAuxiliar);
		usuarioService.salvar(usuarioAuxiliar1);

		List<Usuario> listaDeUsuariosRetornados = usuarioService.buscarPeloLoginOuNome(usuarioEsperado.getLogin());

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

		usuarioService.excluir(usuarioEsperado);
		usuarioService.excluir(usuarioAuxiliar);
		usuarioService.excluir(usuarioAuxiliar1);
	}
	
	@Test
	public void buscarPeloLogin() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");

		usuarioService.salvar(usuarioEsperado);
		usuarioService.salvar(usuarioAuxiliar);
		usuarioService.salvar(usuarioAuxiliar1);

		List<Usuario> listaDeUsuariosRetornados = usuarioService.buscarPeloLogin(usuarioEsperado.getLogin());

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

		usuarioService.excluir(usuarioEsperado);
		usuarioService.excluir(usuarioAuxiliar);
		usuarioService.excluir(usuarioAuxiliar1);
	}
	
	@Test
	public void buscarPeloNome() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		Usuario usuarioAuxiliar = new Usuario("Loka", "pedroka", "senha123");
		Usuario usuarioAuxiliar1 = new Usuario("Pedrona", "lokao", "senha123");

		usuarioService.salvar(usuarioEsperado);
		usuarioService.salvar(usuarioAuxiliar);
		usuarioService.salvar(usuarioAuxiliar1);

		List<Usuario> listaDeUsuariosRetornados = usuarioService.buscarPeloNome(usuarioEsperado.getLogin());

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

		usuarioService.excluir(usuarioEsperado);
		usuarioService.excluir(usuarioAuxiliar);
		usuarioService.excluir(usuarioAuxiliar1);
	}
	
	@Test
	public void buscarPeloLoginExato() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");

		usuarioService.salvar(usuarioEsperado);

		Usuario usuarioRetornado = usuarioService.buscarPeloLoginExato(usuarioEsperado.getLogin());

		assertEquals(usuarioRetornado, usuarioEsperado);

		usuarioService.excluir(usuarioEsperado);
	}
	
	@Test
	public void deve_alterar_um_usuario() throws Exception {
		Usuario usuarioEsperado = new Usuario("Pedro", "pedro", "senha123");
		usuarioService.salvar(usuarioEsperado);
		
		Usuario usuarioRetornadoPeloId = usuarioService
				.buscarPeloId(usuarioEsperado.getId());

		String senhaAntes = usuarioRetornadoPeloId.getSenha();
		String nomeAntes = usuarioRetornadoPeloId.getNome();
		Integer idAntes = usuarioRetornadoPeloId.getId();

		usuarioEsperado.setNome("Pedromodificado");
		usuarioEsperado.setSenha("senha123Modificada");

		usuarioService.salvar(usuarioEsperado);

		usuarioRetornadoPeloId = usuarioService.buscarPeloId(usuarioEsperado.getId());

		String senhaDepois = usuarioRetornadoPeloId.getSenha();
		String nomeDepois = usuarioRetornadoPeloId.getNome();
		Integer idDepois = usuarioRetornadoPeloId.getId();

		Assert.assertTrue(idAntes.equals(idDepois)
				&& !senhaAntes.equals(senhaDepois)
				&& !nomeAntes.equals(nomeDepois));

		usuarioService.excluir(usuarioEsperado);
	}

}
