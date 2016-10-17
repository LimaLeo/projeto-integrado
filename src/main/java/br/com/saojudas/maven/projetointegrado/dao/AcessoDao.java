package br.com.saojudas.maven.projetointegrado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.saojudas.maven.projetointegrado.model.Acesso;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.util.JPAUtil;

public class AcessoDao {
	private EntityManager em;

	public void incluirAcesso(Acesso acesso) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(acesso);
		em.getTransaction().commit();
		em.close();
	}

	public List<Acesso> consultarTodosAcessos() {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Acesso u");
		List<Acesso> acessos = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return acessos;
	}

	public Acesso consultaAcesso(int id) {
		em = new JPAUtil().getEntityManager();
		Acesso acesso = new Acesso();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Acesso u where u.id=:pId");
		query.setParameter("pId", id);
		List<Acesso> acessos = query.getResultList();
		for (Acesso c : acessos) {
			acesso = c;
		}
		em.getTransaction().commit();
		em.close();
		return acesso;
	}
	
	public Acesso consultaAcessoUsuario(Usuario usuario) {
		em = new JPAUtil().getEntityManager();
		Acesso acesso = new Acesso();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Acesso u where u.usuario=:pUsuarioId and u.saida is null");
		query.setParameter("pUsuarioId", usuario);
		List<Acesso> acessos = query.getResultList();
		for (Acesso c : acessos) {
			acesso = c;
		}
		em.getTransaction().commit();
		em.close();
		return acesso;
	}
	
	public Acesso alteraAcessoUsuario(int id, Acesso acessoAlterada) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		// ID do usuario existe no banco de dados
		Acesso acessoExistente = em.find(Acesso.class, id);
		 // commit antes da altera��o
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.merge(acessoAlterada);
		em.getTransaction().commit();
		em.close();
		return acessoExistente;
	}
	
	
}
