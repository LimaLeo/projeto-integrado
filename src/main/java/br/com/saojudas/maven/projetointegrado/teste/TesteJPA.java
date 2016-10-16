package br.com.saojudas.maven.projetointegrado.teste;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.dao.EmpresaDao;
import br.com.saojudas.maven.projetointegrado.dao.UsuarioDao;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.util.JPAUtil;

public class TesteJPA {
	private static EntityManager em;
	private static UsuarioDao usuarioDao;
	private static EmpresaDao empresaDao;
	public static void main(String[] args) {
		empresaDao = new EmpresaDao();
		
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		//Empresa empresa = em.find(Empresa.class, 1);
		Empresa empresa = empresaDao.consultaEmpresa("00000000497606");
		System.out.println(empresa.toString());
		Usuario usuario1 = new Usuario("Leonardo Lima", "42832242863", "Leopard", "1234", "12:00 a 20:00",
				TipoUsuario.SINDICO, true, true, empresa);
		em.persist(usuario1);
		
		em.getTransaction().commit();
		em.close();
	}

}
