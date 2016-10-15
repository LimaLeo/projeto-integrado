package br.com.saojudas.maven.projetointegrado.util;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.dao.ConjuntoDao;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Status;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class PopulaEmpresa {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		Empresa empresa1 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", 21.2, Status.ATIVO);

		Empresa empresa2 = new Empresa("60872504000123", "Banco Itau Holding Financeira S.A", "08:00 a 21:00", 21.2,
				Status.ATIVO);

		Empresa empresa3 = new Empresa("00317929000149", "ABAETÉ Linhas Aéreas S/A", "08:00 a 21:00", 21.2,
				Status.ATIVO);

		Empresa empresa4 = new Empresa("59461152000134", "Banco Itaucred Financiamentos S.A", "08:00 a 21:00", 21.2,
				Status.ATIVO);

		Empresa empresa5 = new Empresa("04020028000141", "GOL Transportes Aéreos S/A", "08:00 a 21:00", 21.2,
				Status.ATIVO);

		Empresa empresa6 = new Empresa("04732914000106", "SETE Linhas Aéreas Ltda", "08:00 a 21:00", 21.2,
				Status.ATIVO);

		Empresa empresa7 = new Empresa("61557039000107", "Itau Seguros S/A", "08:00 a 21:00", 21.2, Status.ATIVO);

		Empresa empresa8 = new Empresa("02428624000130", "TRIP – Transporte Aéreo Regional do Interior Paulista Ltda",
				"08:00 a 21:00", 21.2, Status.ATIVO);

		Empresa empresa9 = new Empresa("00006878000134",
				"Orbitall Servicos E Processamento De Informacoes Comerciais S.A", "08:00 a 21:00", 21.2, Status.ATIVO);

		Empresa empresa10 = new Empresa("08538239000121", "Pró Imóvel Promotora Ltda", "08:00 a 21:00", 21.2,
				Status.ATIVO);

		manager.persist(empresa1);
		manager.persist(empresa2);
		manager.persist(empresa3);
		manager.persist(empresa4);
		manager.persist(empresa5);
		manager.persist(empresa6);
		manager.persist(empresa7);
		manager.persist(empresa8);
		manager.persist(empresa9);
		manager.persist(empresa10);
		
		manager.getTransaction().commit();

		manager.close();

	}
}