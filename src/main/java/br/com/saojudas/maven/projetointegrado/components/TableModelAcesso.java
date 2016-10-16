package br.com.saojudas.maven.projetointegrado.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import br.com.saojudas.maven.projetointegrado.model.Acesso;
import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.model.Acesso;

public class TableModelAcesso extends AbstractTableModel {
	// atributos
	private ArrayList<Acesso> alAcesso;
	private String[] colunas;

	public TableModelAcesso() {
		alAcesso = new ArrayList<Acesso>();
	}

	// adicionar ao arraylist
	public void addAcesso(Acesso acesso) {
		alAcesso.add(acesso);
		fireTableDataChanged();
	}

	public void removeAcesso(int rowIndex) {
		alAcesso.remove(rowIndex);
		fireTableDataChanged();
	}

	public Acesso carregaAcessos(int rowIndex) {
		Acesso usuario = new Acesso();
		usuario = alAcesso.get(rowIndex);

		return usuario;
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return alAcesso.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		/* ATEN��O AP�S CRIAR CLASSE DE NEGOCIO, SUBSTITUIR OS M�TODOS */
		switch (columnIndex) {
		case 0:
			return this.alAcesso.get(rowIndex).getUsuario().getNome();
		case 1:
			return this.alAcesso.get(rowIndex).getUsuario().getCpf();
		case 2:
			return this.alAcesso.get(rowIndex).getUsuario().getEmpresa();
		case 3:
			return this.alAcesso.get(rowIndex).getEntrada();
		case 4:
			return this.alAcesso.get(rowIndex).getSaida();
		default:
			return this.alAcesso.get(rowIndex);
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
	
	public void setAlAcesso(ArrayList<Acesso> acessos) {
		alAcesso = acessos;
	}
}
