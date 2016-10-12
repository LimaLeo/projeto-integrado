package br.com.saojudas.maven.projetointegrado.util;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Status;
import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class PopulaConjunto {

  public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();	
		
		Conjunto conjunto1 = new Conjunto("A", 23, Status.ATIVO);
		Conjunto conjunto2 = new Conjunto("B", 24.8, Status.ATIVO);
		Conjunto conjunto3 = new Conjunto("C", 21.7, Status.ATIVO);
		Conjunto conjunto4 = new Conjunto("D", 17.5, Status.ATIVO);
		Conjunto conjunto5 = new Conjunto("E", 18.9, Status.ATIVO);
		Conjunto conjunto6 = new Conjunto("F", 21.3, Status.ATIVO);
		Conjunto conjunto7 = new Conjunto("G", 22.7, Status.ATIVO);
		Conjunto conjunto8 = new Conjunto("H", 24.9, Status.ATIVO);
		Conjunto conjunto9 = new Conjunto("I", 20.5, Status.ATIVO);
		Conjunto conjunto10 = new Conjunto("J", 20.1, Status.ATIVO);
		Conjunto conjunto11 = new Conjunto("J", 21.1, Status.ATIVO);
		
		// persistindo as contas
		manager.persist(conjunto1);
		manager.persist(conjunto2);
		manager.persist(conjunto3);
		manager.persist(conjunto4);
		manager.persist(conjunto5);
		manager.persist(conjunto6);
		manager.persist(conjunto7);
		manager.persist(conjunto8);
		manager.persist(conjunto9);
		manager.persist(conjunto10);
		manager.persist(conjunto11);
		
		manager.getTransaction().commit();

		manager.close();

	}
}