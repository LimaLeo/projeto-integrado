package br.com.saojudas.maven.projetointegrado.control;

import java.util.List;

import br.com.saojudas.maven.projetointegrado.dao.UsuarioDao;
import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class UsuarioCtrl {
	private UsuarioDao dao = new UsuarioDao();
	
	public void incluirUsuario(Usuario usuario) {
		dao.incluirUsuario(usuario);
	}
	
	public List<Usuario> consultarTodosUsuario() {
		List<Usuario> usuarios = dao.consultarTodosUsuario();
		return usuarios;
	}
	
	public Usuario consultaUsuario(String cpf)
	{	
		Usuario usuario = dao.consultaUsuario(cpf);
		return usuario;
	}
	
	public Usuario consultaUsuarioLogin(String login)
	{	
		Usuario usuario = dao.consultaUsuarioLogin(login);
		return usuario;
	}
	
	public Usuario alteraUsuario(int id, Usuario usuarioAlterado)
	{
		Usuario usuario = dao.alteraUsuario(id, usuarioAlterado);
		return usuario;
	}
}
