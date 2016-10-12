package br.com.saojudas.maven.projetointegrado.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
//import java.util.Scanner;

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
			System.err.println("Voc� tem acesso para criar esse arquivo.");
			System.exit(1);
		} // end catch
		catch (FileNotFoundException filesNotFoundException)
		{
			System.err.println("Erro ao criar arquivo.");
			System.exit(1);
		} // end catch
	}

	// cria login
	public void createLogin(String nome, String senha) throws IOException
	{
		Usuario usuario = new Usuario();
		usuario.setLogin(nome);
		usuario.setSenha(senha);

		InputStream inputStream = new FileInputStream("login.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		OutputStream outputStream = new FileOutputStream("login-tmp.txt");
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		String dados = String.format("%s %s", usuario.getLogin(), usuario.getSenha());

		String linha = bufferedReader.readLine();
		bufferedWriter.append(dados);

		while (linha != null)
		{
			bufferedWriter.newLine();
			bufferedWriter.append(linha);
			linha = bufferedReader.readLine();
		}
		bufferedReader.close();
		bufferedWriter.close();

		File login = new File("login.txt");
		login.delete();

		File loginTmp = new File("login-tmp.txt");
		loginTmp.renameTo(new File("login.txt"));
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

	public void ordenar(String[] vetUsuario)
	{ // object to be written to file
		try // output values to file
		{
			// abre o arquivo de texto
			writer = new FileWriter("login.txt", false);
			for (int i = 0; i < vetUsuario.length; i++)
			{
				// escreve no arquivo de texto
				writer.write(vetUsuario[i] + "\n");
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
	} // end method addRecords
		// close file
} // end class CreateTextFile
