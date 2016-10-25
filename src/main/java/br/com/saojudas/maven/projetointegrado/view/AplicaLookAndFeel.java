package br.com.saojudas.maven.projetointegrado.view;
import com.jtattoo.plaf.*;
import javax.swing.UIManager;
public class AplicaLookAndFeel {
	private AplicaLookAndFeel() {
	}

	 public static void lookAndFeel() {
	 try {
	 // select Look and Feel
	 UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
	 // start application
	 }
	 catch (Exception ex) {
	 ex.printStackTrace();
	 }
	 }
}
