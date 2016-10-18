package br.com.saojudas.maven.projetointegrado.ac;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

public class CreateACFile extends SistemaArCondicionado
{
	private Formatter output;
		
	public void openFile()
	{
		try
		{
			output = new Formatter("OutputAC.txt");
		} 
		catch (SecurityException securityException)
		{
			System.err.println("Voc� tem acesso para criar esse arquivo.");
			System.exit(1);
		} 
		catch (FileNotFoundException filesNotFoundException)
		{
			System.err.println("Erro ao criar arquivo.");
			System.exit(1);
		} 
	}
	
	public void createFile() throws IOException
	{
		try         
		{
			//insere os dados do sistema de AC no arquivo
			output.format( "%s", medirTemperatura());
		}
		catch ( FormatterClosedException formatterClosedException )
		{
			System.err.println( "Erro ao escrever para o arquivo." );             
			return;          
		} 
		catch ( NoSuchElementException elementException )
		{
			System.err.println( "Entrada inv�lida. Por favor tente novamente." );
		} 
	}
		
	public void closeFile()
	{
		if (output != null)
			output.close();
	}
		
	public String medirTemperatura()
	{
		String str = "";
		populaListaAC();
		
		for(int i = 0; i < listaAC.size(); i++)
		{
			str += listaAC.get(i).dados() + "\n";
		}
		
		return str;
	}
}
