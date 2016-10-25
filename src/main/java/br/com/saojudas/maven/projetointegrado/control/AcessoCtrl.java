package br.com.saojudas.maven.projetointegrado.control;

import java.util.List;

import br.com.saojudas.maven.projetointegrado.dao.AcessoDao;
import br.com.saojudas.maven.projetointegrado.model.Acesso;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class AcessoCtrl {
	private AcessoDao dao = new AcessoDao();
	
	public void incluirAcesso(Acesso acesso) {
		dao.incluirAcesso(acesso);
	}
	
	public List<Acesso> consultarTodosAcessos() {
		List<Acesso> acessos = dao.consultarTodosAcessos();
		return acessos;
	}
	
	public List<Acesso> consultarTodosAcessosData(String dataIni, String dataFim) {
		List<Acesso> acessos = dao.consultarTodosAcessosData(dataIni, dataFim);
		return acessos;
	}
	
	public Acesso consultarAcesso(Usuario usuario)
	{
		Acesso acesso = dao.consultaAcessoUsuario(usuario);
		return acesso;
	}
	
	public Acesso alterarAcesso(int id, Acesso acessoAlterar)
	{
		Acesso acesso = dao.alteraAcessoUsuario(id, acessoAlterar);
		return acesso;
	}
	
	
	
	
}
