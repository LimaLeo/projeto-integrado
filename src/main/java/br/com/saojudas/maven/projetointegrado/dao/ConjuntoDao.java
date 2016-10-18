package br.com.saojudas.maven.projetointegrado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.util.JPAUtil;

public class ConjuntoDao {
	private EntityManager em;

	public List<Conjunto> consultarTodosConjuntos() {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Conjunto u");
		List<Conjunto> conjuntos = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return conjuntos;
	}

	public Conjunto consultaConjunto(String bloco) {
		em = new JPAUtil().getEntityManager();
		Conjunto conjunto = new Conjunto();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Conjunto u where u.bloco=:pBloco");
		query.setParameter("pBloco", bloco);
		List<Conjunto> conjuntos = query.getResultList();
		for (Conjunto c : conjuntos) {
			conjunto = c;
		}
		em.getTransaction().commit();
		em.close();
		return conjunto;
	}
	
	public void alteraConjunto(int id, Empresa empresa) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		// ID do usuario existe no banco de dados
		Conjunto conjuntoExistente = em.find(Conjunto.class, id);
		System.out.println(conjuntoExistente.getBloco());
		Empresa empresaExistente = em.find(Empresa.class, empresa.getId());
		// commit antes da altera��o
		em.getTransaction().commit();
		em.getTransaction().begin();
		conjuntoExistente.setEmpresa_conjunto(empresaExistente);
		em.merge(conjuntoExistente);
		em.getTransaction().commit();
		em.close();
	}
	
}
