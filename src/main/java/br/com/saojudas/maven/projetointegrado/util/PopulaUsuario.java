package br.com.saojudas.maven.projetointegrado.util;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class PopulaUsuario {

  public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();	
		Usuario usuario1 = new Usuario("Sara B. Kellum", "63201366692", "Efere1948", "LoeP4caeF8J", "08:00 a 21:00",
				TipoUsuario.SINDICO,true, true, "31431583000139");
		Usuario usuario2 = new Usuario("Ryan Alves Lima", "19168785208", "Andsons", "gethiYe7pai", "08:00 a 21:00",
				TipoUsuario.SINDICO,true, true, "31431583000139");
		
		Usuario usuario3 = new Usuario("Tiago Barros Correia", "23519742985", "Buthrel", "Ziez5habei", "08:00 a 17:30",
				TipoUsuario.FUNCIONARIO,true, true, "61607041000135");
		Usuario usuario4 = new Usuario("Camila Cardoso Pereira", "31396946284", "Thise1935", "giedozo5Ei", "08:00 a 17:30",
				TipoUsuario.FUNCIONARIO,true, true, "61607041000135");
		Usuario usuario5 = new Usuario("Marina Cunha Araujo", "75641584410", "Wasts1984", "ee5IN9aich3", "08:00 a 17:30",
				TipoUsuario.FUNCIONARIO,true, true, "55185130000138");
		Usuario usuario6 = new Usuario("Marisa Silva Barros", "54396288948", "Beffspinget", "lahdo0Mihei", "08:00 a 17:30",
				TipoUsuario.FUNCIONARIO,true, true, "55185130000138");
		Usuario usuario7 = new Usuario("Nicolas Carvalho Fernandes", "78182842069", "Youghten", "JiehuoTh2", "08:00 a 17:30",
				TipoUsuario.FUNCIONARIO,true, true, "55185130000138");
		
		Usuario usuario8 = new Usuario("Murilo Rodrigues Fernandes", "31957291214", "Suittled", "deeriePhee0", "08:00 a 17:30",
				TipoUsuario.ATENDENTE,true, false, "55185130000138");
		Usuario usuario9 = new Usuario("Arthur Costa Rocha", "58489437378", "Jusight", "AiTaGa6b", "08:00 a 17:30",
				TipoUsuario.ATENDENTE,true, false, "55185130000138");
		Usuario usuario10 = new Usuario("Thaís Cunha Barbosa", "36569402749", "Hostall", "IHiVo1tee1N", "08:00 a 17:30",
				TipoUsuario.ATENDENTE,true, false, "55185130000138");
		Usuario usuario11 = new Usuario("Rafaela Ribeiro Melo", "90181178974", "Rege1981", "Oof1od5loh", "08:00 a 17:30",
				TipoUsuario.ATENDENTE,true, false, "55185130000138");
		Usuario usuario12 = new Usuario("Lara Dias Rodrigues", "22768572187", "Luctly", "ahF3vahgh", "08:00 a 17:30",
				TipoUsuario.ATENDENTE,true, false, "55185130000138");

		// persistindo as contas
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