package br.com.saojudas.maven.projetointegrado.ac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadACFile extends SistemaArCondicionado{
	
	private Scanner input;
	
	public void openFile()
	{
		try
		{
			input = new Scanner(new File("InputAC.txt"));
		} 
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			System.exit(1);
		} 
	}
	
	public void readFile()
	{
		ArrayList<ArCondicionado> listAux = listaAC; 
		//listaAC = null;
		str = "";
		try 
		{
			while (input.hasNext())
			{
				String conjunto = input.next();
				int idEmpresa = Integer.parseInt(input.next());
				int temperaturaAtual = Integer.parseInt(input.next());
				int temperaturaReconf = Integer.parseInt(input.next());
				boolean status = Boolean.parseBoolean(input.next());
				
				for(int i = 0; i < listAux.size(); i++)
				{
					if(listAux.get(i).getConjunto().equals(conjunto) && listAux.get(i).getIdEmpresa() == idEmpresa)
					{
						listAux.get(i).setConjunto(conjunto);
						listAux.get(i).setIdEmpresa(idEmpresa);
						listAux.get(i).setTemperaturaAtual(temperaturaAtual);
						listAux.get(i).setStatus(status);
						listAux.get(i).setTemperaturaReconf(temperaturaReconf);
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
	
		for(int j = 0; j < listAux.size(); j++)
		{
			str += listAux.get(j).dados2();
		}
		
	}
	
	public void closeFile()
	{
		if (input != null)
			input.close();
	}
	
	
}
