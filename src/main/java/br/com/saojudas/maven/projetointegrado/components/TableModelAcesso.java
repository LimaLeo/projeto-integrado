package br.com.saojudas.maven.projetointegrado.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class TableModelAcesso extends AbstractTableModel {
	// atributos
	private ArrayList<Usuario> alUsuario;
	private String[] colunas;

	public TableModelAcesso() {
		alUsuario = new ArrayList<Usuario>();
	}

	// adicionar ao arraylist
	public void addUsuario(Usuario usuario) {
		alUsuario.add(usuario);
		fireTableDataChanged();
	}
	public int getColumnCount()
	{
		return colunas.length;
	}
	
	public int getRowCount()
	{
		return alUsuario.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		/* ATEN��O AP�S CRIAR CLASSE DE NEGOCIO, SUBSTITUIR OS M�TODOS */
		switch (columnIndex)
		{
		case 0:
			return this.alUsuario.get(rowIndex).getNome();
		case 1:
			return this.alUsuario.get(rowIndex).getCpf();
		case 2:
			return this.alUsuario.get(rowIndex).getEmpresa();
		case 3:
			return this.alUsuario.get(rowIndex).getTipoUsuario();
		case 4:
			return this.alUsuario.get(rowIndex).getHorarioDeAcesso();
		default:
			return this.alUsuario.get(rowIndex);
		}
	}
	
	public String getColumnName(int columnIndex)
	{
		return this.colunas[columnIndex];
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}
}
