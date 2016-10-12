package br.com.saojudas.maven.projetointegrado.init;

import javax.swing.JFrame;

import br.com.saojudas.maven.projetointegrado.view.TelaPrincipal;

public class App {

	public static void main(String[] args) {
		TelaPrincipal tSCP = new TelaPrincipal();
		tSCP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
