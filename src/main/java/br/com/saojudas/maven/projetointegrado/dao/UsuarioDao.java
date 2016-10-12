package br.com.saojudas.maven.projetointegrado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.util.JPAUtil;

public class UsuarioDao {
	private EntityManager em;

	public void incluirUsuario(Usuario usuario) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public List<Usuario> consultarTodosUsuario() {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Usuario u");
		List<Usuario> usuarios = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return usuarios;
	}

	public Usuario consultaUsuario(String cpf) {
		em = new JPAUtil().getEntityManager();
		Usuario usuario = new Usuario();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Usuario u where u.cpf=:pCpf");
		query.setParameter("pCpf", cpf);
		List<Usuario> usuarios = query.getResultList();
		for(Usuario u : usuarios)
		{	
			usuario = u;
		}
		em.getTransaction().commit();
		em.close();
		return usuario;
	}
	
	public Usuario alteraUsuario(int id, Usuario usuarioAlterado) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		// ID do usuario existe no banco de dados
		Usuario usuarioExistente = em.find(Usuario.class, id);
		 // commit antes da altera��o
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.merge(usuarioAlterado);
		em.getTransaction().commit();
		em.close();
		return usuarioExistente;
	}
}
