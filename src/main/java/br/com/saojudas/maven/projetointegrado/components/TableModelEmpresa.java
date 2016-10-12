package br.com.saojudas.maven.projetointegrado.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import br.com.saojudas.maven.projetointegrado.model.Empresa;

public class TableModelEmpresa extends AbstractTableModel {
	// atributos
	private ArrayList<Empresa> alEmpresa;
	private String[] colunas;

	public TableModelEmpresa() {
		alEmpresa = new ArrayList<Empresa>();
	}

	// adicionar ao arraylist
	public void addEmpresa(Empresa empresa) {
		alEmpresa.add(empresa);
		fireTableDataChanged();
	}
	
	public int getColumnCount()
	{
		return colunas.length;
	}
	
	public int getRowCount()
	{
		return alEmpresa.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex)
		{

		case 0:
			return this.alEmpresa.get(rowIndex).getCnpj();
		case 1:
			return this.alEmpresa.get(rowIndex).getRazaoSocial();
		case 2:
			return this.alEmpresa.get(rowIndex).getHorarioDeFuncionamento();
		case 3:
			return this.alEmpresa.get(rowIndex).getTemperaturaMaximaArCondicionado();
		default:
			return this.alEmpresa.get(rowIndex);
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
