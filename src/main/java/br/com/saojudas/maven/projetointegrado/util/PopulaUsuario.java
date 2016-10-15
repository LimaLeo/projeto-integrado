package br.com.saojudas.maven.projetointegrado.util;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.saojudas.maven.projetointegrado.dao.EmpresaDao;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class PopulaUsuario {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		EmpresaDao empresaDao = new EmpresaDao();
		List<Empresa> empresas = null;
		List<Usuario> usuarios = null;
		for (int i = 1; i <= empresaDao.size(); i++) {
			empresas.add(manager.find(Empresa.class, i));
		}

		manager.getTransaction().begin();
		usuarios.add(new Usuario("Sara B. Kellum", "63201366692", "Efere1948", "LoeP4caeF8J", "08:00 a 21:00",
				TipoUsuario.SINDICO, true, true, empresas.get(1).getCnpj()));
		usuarios.add(new Usuario("Ryan Alves Lima", "19168785208", "Andsons", "gethiYe7pai", "08:00 a 21:00",
				TipoUsuario.SINDICO, true, true, empresas.get(2).getCnpj()));

		usuarios.add(new Usuario("Tiago Barros Correia", "23519742985", "Buthrel", "Ziez5habei", "08:00 a 17:30",
				TipoUsuario.FUNCIONARIO, true, true, empresas.get(3).getCnpj()));
		usuarios.add(new Usuario("Camila Cardoso Pereira", "31396946284", "Thise1935", "giedozo5Ei",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresas.get(4).getCnpj()));
		usuarios.add(new Usuario("Marina Cunha Araujo", "75641584410", "Wasts1984", "ee5IN9aich3",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresas.get(5).getCnpj()));
		usuarios.add(new Usuario("Marisa Silva Barros", "54396288948", "Beffspinget", "lahdo0Mihei",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresas.get(6).getCnpj()));
		usuarios.add(new Usuario("Nicolas Carvalho Fernandes", "78182842069", "Youghten", "JiehuoTh2",
				"08:00 a 17:30", TipoUsuario.FUNCIONARIO, true, true, empresas.get(7).getCnpj()));

		usuarios.add(new Usuario("Murilo Rodrigues Fernandes", "31957291214", "Suittled", "deeriePhee0",
				"08:00 a 17:30", TipoUsuario.ATENDENTE, true, false, empresas.get(8).getCnpj()));
		usuarios.add(new Usuario("Arthur Costa Rocha", "58489437378", "Jusight", "AiTaGa6b", "08:00 a 17:30",
				TipoUsuario.ATENDENTE, true, false, empresas.get(9).getCnpj()));
		usuarios.add(new Usuario("Thaís Cunha Barbosa", "36569402749", "Hostall", "IHiVo1tee1N", "08:00 a 17:30",
				TipoUsuario.ATENDENTE, true, false, empresas.get(1).getCnpj()));
		usuarios.add(new Usuario("Rafaela Ribeiro Melo", "90181178974", "Rege1981", "Oof1od5loh",
				"08:00 a 17:30", TipoUsuario.ATENDENTE, true, false, empresas.get(2).getCnpj()));
		usuarios.add(new Usuario("Lara Dias Rodrigues", "22768572187", "Luctly", "ahF3vahgh", "08:00 a 17:30",
				TipoUsuario.ATENDENTE, true, false, empresas.get(3).getCnpj()));

		// persistindo as contas
		for(int i=1;i<=usuarios.size();i++)
		{
			manager.persist(usuarios.get(i));
		}

		manager.getTransaction().commit();

		manager.close();

	}
}