package br.com.saojudas.maven.projetointegrado.util;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.dao.EmpresaDao;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Status;

public class PopulaConjunto {

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
		
		Conjunto conjunto1 = new Conjunto("A", 23, Status.ATIVO, empresa1);
		Conjunto conjunto2 = new Conjunto("B", 24.8, Status.ATIVO, empresa2);
		Conjunto conjunto3 = new Conjunto("C", 21.7, Status.ATIVO, empresa3);
		Conjunto conjunto4 = new Conjunto("D", 17.5, Status.ATIVO, empresa4);
		Conjunto conjunto5 = new Conjunto("E", 18.9, Status.ATIVO, empresa5);
		Conjunto conjunto6 = new Conjunto("F", 21.3, Status.ATIVO, empresa6);
		Conjunto conjunto7 = new Conjunto("G", 22.7, Status.ATIVO, empresa7);
		Conjunto conjunto8 = new Conjunto("H", 24.9, Status.ATIVO, empresa8);
		Conjunto conjunto9 = new Conjunto("I", 20.5, Status.ATIVO, empresa9);
		Conjunto conjunto10 = new Conjunto("J", 20.1, Status.ATIVO, empresa10);
		Conjunto conjunto11 = new Conjunto("J", 21.1, Status.ATIVO,empresa1);
		
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