package br.com.saojudas.maven.projetointegrado.control;

import java.util.List;

import br.com.saojudas.maven.projetointegrado.dao.ConjuntoDao;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Empresa;

public class ConjuntoCtrl {
	private ConjuntoDao dao = new ConjuntoDao();
	
	public List<Conjunto> consultarTodosConjuntos() {
		List<Conjunto> conjuntos = dao.consultarTodosConjuntos();
		return conjuntos;
	}
	
	public Conjunto consultaConjunto(String bloco){
		Conjunto conjunto = dao.consultaConjunto(bloco);
		return conjunto;
	}
	
	public void alteraConjunto(int id,Empresa empresa) {
		dao.alteraConjunto(id, empresa);
	}
}
