package br.com.saojudas.maven.projetointegrado.control;

import java.util.List;

import br.com.saojudas.maven.projetointegrado.dao.EmpresaDao;
import br.com.saojudas.maven.projetointegrado.model.Empresa;

public class EmpresaCtrl {
	private EmpresaDao dao = new EmpresaDao();
	
	public void incluirEmpresa(Empresa empresa) {
		dao.incluirEmpresa(empresa);
	}
	
	public List<Empresa> consultarTodasEmpresas() {
		List<Empresa> empresas = dao.consultarTodasEmpresas();
		return empresas;
	}
	
	public Empresa consultaEmpresa(String cnpj)
	{	
		Empresa empresa = dao.consultaEmpresa(cnpj);
		return empresa;
	}
	
	public Empresa alteraEmpresa(int id, Empresa empresaAlterada)
	{
		Empresa empresa = dao.alteraEmpresa(id, empresaAlterada);
		return empresa;
	}
}
