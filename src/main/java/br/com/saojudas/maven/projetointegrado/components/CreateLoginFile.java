package br.com.saojudas.maven.projetointegrado.components;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
//import java.util.Scanner;
import javax.swing.event.ListSelectionEvent;

import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.view.TelaPrincipal;

public class CreateLoginFile
{
	private Formatter output;
	private FileWriter writer;

	// atributo para manipulacao de idiomas
	private ResourceBundle bn = TelaPrincipal.bn;

	public void openFile()
	{
		try
		{
			output = new Formatter("login.txt");
		} // end try
		catch (SecurityException securityException)
		{
			System.err.println("Você tem acesso para criar esse arquivo.");
			System.exit(1);
		} // end catch
		catch (FileNotFoundException filesNotFoundException)
		{
			System.err.println("Erro ao criar arquivo.");
			System.exit(1);
		} // end catch
	}

	// cria login
	public void criarArquivoAcesso(List<Usuario> listaUsuario)
	{		
		try // output values to file
		{
			// abre o arquivo de texto
			writer = new FileWriter("login.txt", false);
			
			for (Usuario usuario : listaUsuario)
			{
				// escreve no arquivo de texto
				writer.write(usuario.getLogin() + " " + usuario.getSenha() + "\n");
			}
			// fecha o arquivo de texto
			writer.close();

		} // end try
		catch (FormatterClosedException formatterClosedException)
		{
			System.err.println("Erro ao escrever para o arquivo.");
			return;
		} // end catch
		catch (NoSuchElementException elementException)
		{
			System.err.println("Entrada inv�lida. Por favor tente novamente.");
		} // end catch
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Arquivo de acessos enviado!");
	}

	public void addRecords(String usuario, String senha)
	{ // object to be written to file
		Usuario record = new Usuario();
		try // output values to file
		{

			// abre o arquivo de texto
			writer = new FileWriter("login.txt", true);
			record.setLogin(usuario);
			record.setSenha(senha);
			// escreve no arquivo de texto
			writer.write("\n" + record.getLogin() + " " + record.getSenha());
			// fecha o arquivo de texto
			writer.close();

		} // end try
		catch (FormatterClosedException formatterClosedException)
		{
			System.err.println("Erro ao escrever para o arquivo.");
			return;
		} // end catch
		catch (NoSuchElementException elementException)
		{
			System.err.println("Entrada inv�lida. Por favor tente novamente.");
		} // end catch
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, bn.getString("createLoginFile.message.incluido"));
	} // end method addRecords
		// close file

	public void closeFile()
	{
		if (output != null)
			output.close();
	} // end method closeFile

} // end class CreateTextFile
