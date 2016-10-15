package br.com.saojudas.maven.projetointegrado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.saojudas.maven.projetointegrado.model.Conjunto;
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

	public Conjunto consultaUsuario(int id) {
		em = new JPAUtil().getEntityManager();
		Conjunto conjunto = new Conjunto();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Conjunto u where u.id=:pId");
		query.setParameter("pId", id);
		List<Conjunto> conjuntos = query.getResultList();
		for(Conjunto c : conjuntos)
		{	
			conjunto = c;
		}
		em.getTransaction().commit();
		em.close();
		return conjunto;
	}
	
	public int size(){
		em = new JPAUtil().getEntityManager();
		Conjunto conjunto = new Conjunto();
		em.getTransaction().begin();
		String consulta = "SELECT COUNT(u) FROM Conjunto u";
		TypedQuery<Number> query = em.createQuery(consulta, Number.class);
		Number result = query.getSingleResult();
		
		int tamanho =  result.intValue();
		em.getTransaction().commit();
		em.close();
		
		return tamanho;
	}
}
