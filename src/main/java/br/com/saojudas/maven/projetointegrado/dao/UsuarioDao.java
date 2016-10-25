package br.com.saojudas.maven.projetointegrado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	public List<Usuario> consultarTodosUsuarioNome(String nome) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Usuario u where u.nome like '%" + nome + "%'");
		List<Usuario> usuarios = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return usuarios;
	}
	
	public List<Usuario> consultarTodosUsuarioCpf(String cpf) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Usuario u where u.cpf = '" + cpf + "'");
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
	
	public Usuario consultaUsuarioLogin(String login) {
		em = new JPAUtil().getEntityManager();
		Usuario usuario = new Usuario();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Usuario u where u.login=:pLogin");
		query.setParameter("pLogin", login);
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
	
	public int size(){
		
		em = new JPAUtil().getEntityManager();
		Usuario usuario = new Usuario();
		em.getTransaction().begin();
		String consulta = "SELECT COUNT(a) FROM Usuario u";
		TypedQuery<Number> query = em.createQuery(consulta, Number.class);
		Number result = query.getSingleResult();
		
		int tamanho =  result.intValue();
		em.getTransaction().commit();
		em.close();
		
		return tamanho;
	}
}
