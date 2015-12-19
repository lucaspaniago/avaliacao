package avaliacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.avaliacao.model.entidade.Usuario;

public class TestDataSource {

	public static void main(String[] args) {
		// Carregando o contexto do spring
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/springbeans.xml");

		EntityManagerFactory emf = (EntityManagerFactory) ctx
				.getBean("entityManagerFactory");

		// Obtendo um objeto gerenciador de entidades
		EntityManager em = emf.createEntityManager();

		// Criando um objeto Usuario
		Usuario usu = new Usuario("Maria", "maria", "senha123");

		// Obtendo um objeto gerenciador de transacoes
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(usu);
		transaction.commit();

		ctx.close();
		
	}

	@SuppressWarnings("unused")
	private static void testDataSource() {
		
	}

	// Isso era a main antiga:
	// public static void main(String[] args) {
	// // Carregando o contexto do spring
	// ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
	// "file:src/main/webapp/WEB-INF/springbeans.xml");
	//
	// // Acessando o objeto BasicDataSource criado dentro do contexto do
	// // Spring
	// BasicDataSource basicDataSource = (BasicDataSource) ctx
	// .getBean("dataSource");
	//
	// // Imprimindo dados do objeto BasicDataSource criado no contexto do
	// // Spring
	// System.out.println(basicDataSource.getDriverClassName());
	// System.out.println(basicDataSource.getUrl());
	// System.out.println(basicDataSource.getUsername());
	// System.out.println(basicDataSource.getPassword());
	//
	// // O objeto de carregamento de Recursos ClassPathXmlApplicationContext
	// // deve ser fechado após o seu uso.
	// ctx.close();
	// }
}

