package br.com.saojudas.maven.projetointegrado.components;

// Fig. 14.11: ReadTextFile.java
// This program reads a text file and displays each record.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.saojudas.maven.projetointegrado.model.Usuario;
import br.com.saojudas.maven.projetointegrado.view.TelaPrincipal;

public class ReadLoginFile
{
	private Scanner input;
	private static boolean gerente;
	
	// internacionaliza��o
	static ResourceBundle bn = TelaPrincipal.bn;

	// enable user to open file
	public void openFile()
	{
		try
		{
			input = new Scanner(new File("login.txt"));
		} // end try
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			System.exit(1);
		} // end catch
	} // end method openFile
		// read record from file

	public void readRecords()
	{ // object to be written to screen
		Usuario record = new Usuario();
		System.out.printf("%-12s%-12s\n", "Usu�rio", "Senha");
		try // read records from file using Scanner object
		{
			while (input.hasNext())
			{
				;
				record.setLogin(input.next());
				record.setSenha(input.next());
				// display record contents
				System.out.printf("%-12s%-10s\n", record.getLogin(), record.getSenha());
			} // end while
		} // end try
		catch (NoSuchElementException elementException)
		{
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		} // end catch
		catch (IllegalStateException stateException)
		{
			System.err.println("Error reading from file.");
			System.exit(1);
		} // end catch
	} // end method readRecords
		// close file and terminate application

	// valida o Login e senha digitados com os cadastrados
	public boolean validaDadosLogin(String login, String senha)
	{
		String vet[] = getVetLoginOrdenado();
		if(buscaBinaria(vet, login + " " + senha) != -1)
		{
			JOptionPane.showMessageDialog(null, bn.getString("readLoginFile.message.loginRealizado"));
			return true;		
		}
		JOptionPane.showMessageDialog(null, bn.getString("readLoginFile.message.loginNaoRealizado"));
		return false;
	}

	public boolean alteraLogin(String login, String senha)
	{
		Usuario record = new Usuario();
		try // read records from file using Scanner object
		{
			while (input.hasNext())
			{
				record.setLogin(input.next());
				record.setSenha(input.next());
				// display record contents
				if (login.equals(record.getLogin()) && senha.equals(record.getSenha()))
				{
					JOptionPane.showMessageDialog(null, "LOGIN REALIZADO COM SUCESSO");
					return true;
				}
			} // end while
			JOptionPane.showMessageDialog(null, "LOGIN E/OU SENHA ERRADO(S)");
			return false;
		} // end try
		catch (NoSuchElementException elementException)
		{
			JOptionPane.showMessageDialog(null, "File improperly formed.");
			input.close();
			System.exit(1);
			return false;
		} // end catch
		catch (IllegalStateException stateException)
		{
			JOptionPane.showMessageDialog(null, "Error reading from file.");
			System.exit(1);
			return false;
		} // end catch
	}

	public void closeFile()
	{
		if (input != null)
			input.close(); // close file
	} // end method closeFile

	// Ordenar txt
	public String[] getVetLoginOrdenado()
	{
		ArrayList<String> arrayUsuario = new ArrayList<String>();

		Usuario record = new Usuario();
		try // read records from file using Scanner object
		{
			while (input.hasNext())
			{
				record.setLogin(input.next());
				record.setSenha(input.next());

				// adiciona objeto no arrayList
				arrayUsuario.add(record.getLogin() + " " + record.getSenha());

			} // end while
		} // end try
		catch (NoSuchElementException elementException)
		{
			JOptionPane.showMessageDialog(null, "File improperly formed.");
			input.close();
			System.exit(1);
		} // end catch
		catch (IllegalStateException stateException)
		{
			JOptionPane.showMessageDialog(null, "Error reading from file.");
			System.exit(1);
		} // end catch

		// vetor para receber as strings do array
		String[] vet = new String[arrayUsuario.size()];

		for (int i = 0; i < vet.length; i++)
		{
			vet[i] = arrayUsuario.get(i);
		}

		// ordenar array

		Arrays.sort(vet);

		return vet;
	}

	// busca bin�ria
	public static int buscaBinaria(String vet[], String procurado)
	{
		int inicio = 0;
		int fim = vet.length - 1;

		while (inicio <= fim)
		{
			int meio = (inicio + fim) / 2;

			if (vet[meio].equals(procurado))
				return meio;
			else if (vet[meio].compareTo(procurado) < 0)
				inicio = meio + 1;
			else
				fim = meio - 1;
		}
		return -1;
	}

} // end class ReadTextFile