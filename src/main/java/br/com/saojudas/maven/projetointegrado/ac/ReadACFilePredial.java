package br.com.saojudas.maven.projetointegrado.ac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.saojudas.maven.projetointegrado.dao.ConjuntoDao;
import br.com.saojudas.maven.projetointegrado.model.Conjunto;

public class ReadACFilePredial extends ConjuntoAC{
	
	private Scanner input;
	protected static String str;
			
	public void openFile()
	{
		try
		{
			input = new Scanner(new File("OutputAC.txt"));
		} 
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			System.exit(1);
		} 
	}
	
	public void readFile()
	{
		ConjuntoDao cdao = new ConjuntoDao();
		listaACConjunto = (ArrayList<Conjunto>) cdao.consultarTodosConjuntos();
		str = "";
		
		try 
		{
			while (input.hasNext())
			{
				String conjunto = input.next();
				int idEmpresa = Integer.parseInt(input.next());
				int temperaturaAtual = Integer.parseInt(input.next());
								
				for(int i = 0; i < listaACConjunto.size(); i++)
				{
					if(listaACConjunto.get(i).getBloco().equals(conjunto) && listaACConjunto.get(i).getEmpresa_conjunto().getId() == idEmpresa)
					{
						if(temperaturaAtual >= listaACConjunto.get(i).getTemperaturaMedia())
						{
							str += conjunto + " " + idEmpresa + " " + temperaturaAtual + " " + listaACConjunto.get(i).getTemperaturaMedia() + " " + "true" + "\n";
						}
						else
						{
							str += conjunto + " " + idEmpresa + " " + temperaturaAtual + " " + listaACConjunto.get(i).getTemperaturaMedia() + " " + "false" + "\n";
						}
					}
				}
				
			} 
		} 
		catch (NoSuchElementException elementException)
		{
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		} 
		catch (IllegalStateException stateException)
		{
			System.err.println("Error reading from file.");
			System.exit(1);
		}
	}
	
	public void closeFile()
	{
		if (input != null)
			input.close(); 
	}
	
	public static String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
}
