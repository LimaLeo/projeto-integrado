package br.com.saojudas.maven.projetointegrado.util;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.dao.EmpresaDao;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class PopulaUsuario {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();

		Empresa empresa1 = manager.find(Empresa.class,1);
		Empresa empresa2 = manager.find(Empresa.class,2);
		Empresa empresa3 = manager.find(Empresa.class,3);
		Empresa empresa4 = manager.find(Empresa.class,4);
		Empresa empresa5 = manager.find(Empresa.class,5);
		Empresa empresa6 = manager.find(Empresa.class,6);
		Empresa empresa7 = manager.find(Empresa.class,7);
		Empresa empresa8 = manager.find(Empresa.class,8);
		Empresa empresa9 = manager.find(Empresa.class,9);
		Empresa empresa10 = manager.find(Empresa.class,10);
		
		Usuario usuario1 = new Usuario("Ryan Alves Lima", "19168785208", "Andsons", "gethiYe7pai", "08:00 a 21:00",
				TipoUsuario.SINDICO, true, true);

		Usuario usuario2 = new Usuario("Tiago Barros Correia", "23519742985", "Buthrel", "Ziez5habei", "08:00 a 17:30",
				TipoUsuario.FUNCIONARIO, true, true, empresa3);
		
		Usuario usuario3 = new Usuario("Camila Cardoso Pereira", "31396946284", "Thise1935", "giedozo5Ei",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresa4);
		
		Usuario usuario4 = new Usuario("Marina Cunha Araujo", "75641584410", "Wasts1984", "ee5IN9aich3",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresa5);
				
		Usuario usuario5 = new Usuario("Marisa Silva Barros", "54396288948", "Beffspinget", "lahdo0Mihei",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresa6);
		
		Usuario usuario6 = new Usuario("Nicolas Carvalho Fernandes", "78182842069", "Youghten", "JiehuoTh2",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresa7);

		Usuario usuario7 = new Usuario("Murilo Rodrigues Fernandes", "31957291214", "Suittled", "deeriePhee0",
				"08:00 a 17:30", TipoUsuario.ATENDENTE, true, false, empresa8);
				
		Usuario usuario8 = new Usuario("Arthur Costa Rocha", "58489437378", "Jusight", "AiTaGa6b", "08:00 a 17:30",
				TipoUsuario.ATENDENTE, true, false, empresa9);
		
		Usuario usuario9 = new Usuario("Thaís Cunha Barbosa", "36569402749", "Hostall", "IHiVo1tee1N", "08:00 a 17:30",
				TipoUsuario.ATENDENTE, true, false, empresa10);
				
		Usuario usuario10 = new Usuario("Rafaela Ribeiro Melo", "90181178974", "Rege1981", "Oof1od5loh",
				"08:00 a 17:30", TipoUsuario.ATENDENTE, true, false,empresa2);
		
		Usuario usuario11 = new Usuario("Lara Dias Rodrigues", "22768572187", "Luctly", "ahF3vahgh", "08:00 a 17:30",
				TipoUsuario.ATENDENTE, true, false, empresa1);

		Usuario usuario12 = new Usuario("Sara B. Kellum", "63201366692", "Efere1948", "LoeP4caeF8J", "08:00 a 21:00",
				TipoUsuario.SINDICO, true, true, empresa3);

		manager.persist(usuario1);
		manager.persist(usuario2);
		manager.persist(usuario3);
		manager.persist(usuario4);
		manager.persist(usuario5);
		manager.persist(usuario6);
		manager.persist(usuario7);
		manager.persist(usuario8);
		manager.persist(usuario9);
		manager.persist(usuario10);
		manager.persist(usuario11);
		manager.persist(usuario12);

		manager.getTransaction().commit();

		manager.close();

	}
}