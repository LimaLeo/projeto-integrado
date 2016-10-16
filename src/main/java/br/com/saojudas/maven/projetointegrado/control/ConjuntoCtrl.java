package br.com.saojudas.maven.projetointegrado.control;

import java.util.List;

import br.com.saojudas.maven.projetointegrado.dao.ConjuntoDao;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class ConjuntoCtrl {
	private ConjuntoDao dao = new ConjuntoDao();
	
	public List<Conjunto> consultarTodosConjuntos() {
		List<Conjunto> conjuntos = dao.consultarTodosConjuntos();
		return conjuntos;
	}
	
	// public Usuario alteraConjunto(int id, Conjunto cojuntoAlterado)
	// {
	// Conjunto conjunto = dao.alteraConjunto(id, cojuntoAlterado);
	// return conjunto;
	// }
}
