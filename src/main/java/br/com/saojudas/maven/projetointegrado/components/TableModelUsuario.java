package br.com.saojudas.maven.projetointegrado.components;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class TableModelUsuario extends AbstractTableModel {
	// atributos
	private ArrayList<Usuario> alUsuario;
	private String[] colunas;

	public TableModelUsuario() {
		alUsuario = new ArrayList<Usuario>();
	}

	// adicionar ao arraylist
	public void addUsuario(Usuario usuario) {
		alUsuario.add(usuario);
		fireTableDataChanged();
	}

	public void removeUsuario(int rowIndex) {
		alUsuario.remove(rowIndex);
		fireTableDataChanged();
	}
	
	public Usuario carregaUsuario(int rowIndex) {
		Usuario usuario = new Usuario();
		usuario = alUsuario.get(rowIndex);
		
		return usuario;
	}

	public void procuraUsuario(String cpf, String nome) {
		ArrayList<Usuario> temp = new ArrayList<Usuario>();
		boolean encontrouCpf = false, encontrouNome = false;
		for (Usuario u : alUsuario) {
			Usuario usuario = new Usuario();
			if (u.getCpf().replace(".", "").replace("-", "").equals(cpf)) {
				temp.clear(); usuario = u;
				temp.add(usuario);
				encontrouCpf = true;
				break;
			} else if (u.getNome().toLowerCase().contains(nome.toLowerCase())) {
				usuario = u;
				temp.add(usuario);
				encontrouNome = true;
			}
		}
		if (!encontrouCpf && !encontrouNome) {
			temp.clear();
		}
		if (temp.isEmpty() && !encontrouCpf) {
			JOptionPane.showMessageDialog(null, "Usuario nao foi encontrado, tente novamente!", "Erro ao pesquisar",
					JOptionPane.ERROR_MESSAGE);
		}
		setAlUsuario(temp);
		fireTableDataChanged();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return alUsuario.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.alUsuario.get(rowIndex).getTipoUsuario();
		case 1:
			return this.alUsuario.get(rowIndex).getNome();
		case 2:
			return this.alUsuario.get(rowIndex).getCpf();
		default:
			return this.alUsuario.get(rowIndex);
		}
	}

	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	public void setAlUsuario(ArrayList<Usuario> usuarios) {
		alUsuario = usuarios;
	}
}
