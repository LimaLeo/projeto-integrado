package br.com.saojudas.maven.projetointegrado.util;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Status;
import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class PopulaEmpresa {

  public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		
		Conjunto conjunto1 = manager.find(Conjunto.class, 1);
		Conjunto conjunto2 = manager.find(Conjunto.class, 2);
		Conjunto conjunto3 = manager.find(Conjunto.class, 3);
		Conjunto conjunto4 = manager.find(Conjunto.class, 4);
		Conjunto conjunto5 = manager.find(Conjunto.class, 5);
		Conjunto conjunto6 = manager.find(Conjunto.class, 6);
		Conjunto conjunto7 = manager.find(Conjunto.class, 7);
		Conjunto conjunto8 = manager.find(Conjunto.class, 8);
		Conjunto conjunto9 = manager.find(Conjunto.class, 9);
		Conjunto conjunto10 = manager.find(Conjunto.class, 10);
		Conjunto conjunto11 = manager.find(Conjunto.class, 11);

		manager.getTransaction().begin();	
		
		Empresa empresa1 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", conjunto1, 21.2 ,Status.ATIVO);
		empresa1.addConjunto(conjunto11);
		
		Empresa empresa2 = new Empresa("60872504000123", "Banco Itau Holding Financeira S.A", "08:00 a 21:00", conjunto2, 21.2 ,Status.ATIVO);
		Empresa empresa3 = new Empresa("00317929000149", "ABAETÉ Linhas Aéreas S/A", "08:00 a 21:00", conjunto3, 21.2 ,Status.ATIVO);
		Empresa empresa4 = new Empresa("59461152000134", "Banco Itaucred Financiamentos S.A", "08:00 a 21:00", conjunto4, 21.2 ,Status.ATIVO);
		Empresa empresa5 = new Empresa("04020028000141", "GOL Transportes Aéreos S/A", "08:00 a 21:00", conjunto5, 21.2 ,Status.ATIVO);
		Empresa empresa6 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", conjunto6, 21.2 ,Status.ATIVO);
		Empresa empresa7 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", conjunto7, 21.2 ,Status.ATIVO);
		Empresa empresa8 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", conjunto8, 21.2 ,Status.ATIVO);
		Empresa empresa9 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", conjunto9, 21.2 ,Status.ATIVO);
		Empresa empresa10 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", conjunto10, 21.2 ,Status.ATIVO);
		
		// persistindo as contas
		manager.persist(empresa1);
		
		manager.getTransaction().commit();

		manager.close();

	}
}