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
		ConjuntoDao conjuntoDao = new ConjuntoDao();
		List<Conjunto> conjuntos = null;
		List<Empresa> empresas = null;

		for (int i = 1; i <= conjuntoDao.size(); i++) {
			conjuntos.add(manager.find(Conjunto.class, i));
		}

		manager.getTransaction().begin();

		Empresa empresa1 = new Empresa("00000000497606", "BANCO DO BRASIL SA", "08:00 a 21:00", conjuntos.get(1), 21.2,
				Status.ATIVO);
		empresa1.addConjunto(conjuntos.get(11));

		empresas.add(new Empresa("60872504000123", "Banco Itau Holding Financeira S.A", "08:00 a 21:00",
				conjuntos.get(2), 21.2, Status.ATIVO));
		empresas.add(new Empresa("00317929000149", "ABAETÉ Linhas Aéreas S/A", "08:00 a 21:00", conjuntos.get(3),
				21.2, Status.ATIVO));
		empresas.add(new Empresa("59461152000134", "Banco Itaucred Financiamentos S.A", "08:00 a 21:00",
				conjuntos.get(4), 21.2, Status.ATIVO));
		empresas.add(new Empresa("04020028000141", "GOL Transportes Aéreos S/A", "08:00 a 21:00",
				conjuntos.get(4), 21.2, Status.ATIVO));
		empresas.add(new Empresa("04732914000106", "SETE Linhas Aéreas Ltda", "08:00 a 21:00", conjuntos.get(5),
				21.2, Status.ATIVO));
		empresas.add(new Empresa("61557039000107", "Itau Seguros S/A", "08:00 a 21:00", conjuntos.get(6), 21.2,
				Status.ATIVO));
		empresas.add(new Empresa("02428624000130", "TRIP – Transporte Aéreo Regional do Interior Paulista Ltda",
				"08:00 a 21:00", conjuntos.get(7), 21.2, Status.ATIVO));
		empresas.add(new Empresa("00006878000134",
				"Orbitall Servicos E Processamento De Informacoes Comerciais S.A", "08:00 a 21:00", conjuntos.get(8),
				21.2, Status.ATIVO));
		empresas.add(new Empresa("08538239000121", "Pró Imóvel Promotora Ltda", "08:00 a 21:00",
				conjuntos.get(9), 21.2, Status.ATIVO));

		// persistindo as contas
		for(int i=1;i<= empresas.size();i++)
		{
			manager.persist(empresas.get(i));
		}

		manager.getTransaction().commit();

		manager.close();

	}
}