package br.com.saojudas.maven.projetointegrado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.util.JPAUtil;

public class EmpresaDao {
	private EntityManager em;

	public void incluirEmpresa(Empresa empresa) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
		em.close();
	}

	public List<Empresa> consultarTodasEmpresas() {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Empresa u");
		List<Empresa> empresas = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return empresas;
	}

	public Empresa consultaEmpresa(String cnpj) {
		em = new JPAUtil().getEntityManager();
		Empresa empresa = new Empresa();
		em.getTransaction().begin();
		Query query = em.createQuery("select u from Empresa u where u.cnpj=:pCnpj");
		query.setParameter("pCnpj", cnpj);
		List<Empresa> empresas = query.getResultList();
		for(Empresa u : empresas)
		{	
			empresa = u;
		}
		em.getTransaction().commit();
		em.close();
		return empresa;
	}
	
	public Empresa alteraEmpresa(int id, Empresa empresaAlterada) {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		// ID do usuario existe no banco de dados
		Empresa empresaExistente = em.find(Empresa.class, id);
		 // commit antes da altera��o
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.merge(empresaAlterada);
		em.getTransaction().commit();
		em.close();
		return empresaExistente;
	}
	
	public int size(){
		
		em = new JPAUtil().getEntityManager();
		Empresa empresa = new Empresa();
		em.getTransaction().begin();
		String consulta = "SELECT COUNT(a) FROM Empresa u";
		TypedQuery<Number> query = em.createQuery(consulta, Number.class);
		Number result = query.getSingleResult();
		
		int tamanho =  result.intValue();
		em.getTransaction().commit();
		em.close();
		
		return tamanho;
	}
}
