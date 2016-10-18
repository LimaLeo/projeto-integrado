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
		Conjunto conjunto2 = new Conjunto("B", 24, Status.ATIVO, empresa2);
		Conjunto conjunto3 = new Conjunto("C", 21, Status.ATIVO, empresa3);
		Conjunto conjunto4 = new Conjunto("D", 17, Status.ATIVO, empresa4);
		Conjunto conjunto5 = new Conjunto("E", 18, Status.ATIVO, empresa5);
		Conjunto conjunto6 = new Conjunto("F", 21, Status.ATIVO, empresa6);
		Conjunto conjunto7 = new Conjunto("G", 22, Status.ATIVO, empresa7);
		Conjunto conjunto8 = new Conjunto("H", 24, Status.ATIVO, empresa8);
		Conjunto conjunto9 = new Conjunto("I", 20, Status.ATIVO, empresa9);
		Conjunto conjunto10 = new Conjunto("J", 20, Status.ATIVO, empresa10);
		
		Conjunto conjunto11 = new Conjunto("K", 18, Status.ATIVO);
		Conjunto conjunto12 = new Conjunto("L", 20, Status.ATIVO);
		Conjunto conjunto13 = new Conjunto("M", 20, Status.ATIVO);
		Conjunto conjunto14 = new Conjunto("N", 23, Status.ATIVO);
		Conjunto conjunto15 = new Conjunto("O", 17, Status.ATIVO);
		Conjunto conjunto16 = new Conjunto("P", 22, Status.ATIVO);
		Conjunto conjunto17 = new Conjunto("Q", 20, Status.ATIVO);
		Conjunto conjunto18 = new Conjunto("R", 20, Status.ATIVO);
		Conjunto conjunto19 = new Conjunto("S", 20, Status.ATIVO);
		
		
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
		manager.persist(conjunto12);
		manager.persist(conjunto13);
		manager.persist(conjunto14);
		manager.persist(conjunto15);
		manager.persist(conjunto16);
		manager.persist(conjunto17);
		manager.persist(conjunto18);
		manager.persist(conjunto19);
		
		manager.getTransaction().commit();

		manager.close();

	}
}