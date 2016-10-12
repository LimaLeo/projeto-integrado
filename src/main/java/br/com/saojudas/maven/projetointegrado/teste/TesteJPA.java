package br.com.saojudas.maven.projetointegrado.teste;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {

		double inicio = System.currentTimeMillis();

		Usuario usuario = new Usuario();
		usuario.setNome("Leonardo Lima");
		usuario.setCpf("42832242863");
		usuario.setLogin("leo");
		usuario.setSenha("123");
		usuario.setHorarioDeAcesso("De 08h a 12h");
		usuario.setTipoUsuario(TipoUsuario.SINDICO);
		usuario.setAcessoLivre(true);
		usuario.setPermissaoAlterarTemperatura(true);

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		em.persist(usuario);

		em.getTransaction().commit();

		em.close();

		double fim = System.currentTimeMillis();
		System.out.println("Executado em: " + (fim - inicio) / 1000 + "s");
	}

}
