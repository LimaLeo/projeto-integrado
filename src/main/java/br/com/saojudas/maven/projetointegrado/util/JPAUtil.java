package br.com.saojudas.maven.projetointegrado.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("controle_predial");
	
	public EntityManager getEntityManager (){
		return entityManagerFactory.createEntityManager();
	}
}
