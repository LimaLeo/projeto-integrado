package br.com.saojudas.maven.projetointegrado.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.saojudas.maven.projetointegrado.components.CryptoAES;
import br.com.saojudas.maven.projetointegrado.components.ReadLoginFile;
import br.com.saojudas.maven.projetointegrado.view.AplicaLookAndFeel;

public class TelaLogin extends JDialog implements ActionListener {

	static ResourceBundle bn = TelaPrincipal.bn;

	// atributos para formulario
	private JButton bt;
	private JTextField loginJText;
	private JPasswordField senhaJText;
	private JLabel loginJLabel, senhaJLabel, apresentacaoJLabel;
	private JComboBox idioma;

	// atributos paineis
	private JPanel header, content;

	// atributo de login
	private ReadLoginFile application;
	
	// atributo para realizar login
	private static boolean statusLogin;

	public TelaLogin(JFrame fr) {
		// invoca o m�todo construtor da superclasse
		super(fr, true);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		AplicaLookAndFeel.lookAndFeel();
		
		// vetor strings de idimas
		String[] idiomas = { bn.getString("telaPrincipal.menuitem.portugues"),
				bn.getString("telaPrincipal.menuitem.ingles"), bn.getString("telaPrincipal.menuitem.espanhol") };

		// instancia JComboBox
		idioma = new JComboBox(idiomas);

		idioma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIdioma(idioma.getSelectedIndex());
				setComponentText();
			}
		});

		// instancia itens de formulario
		bt = new JButton();
		getRootPane().setDefaultButton(bt);

		apresentacaoJLabel = new JLabel();
		loginJLabel = new JLabel();
		senhaJLabel = new JLabel();

		loginJText = new JTextField(15);
		senhaJText = new JPasswordField(15);

		// edicao de titulo
		apresentacaoJLabel.setAlignmentX(RIGHT_ALIGNMENT);
		apresentacaoJLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		apresentacaoJLabel.setAlignmentX(CENTER_ALIGNMENT);
		apresentacaoJLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// instancia paineis
		header = new JPanel(new GridLayout(1, 1, 10, 10));
		content = new JPanel(new GridBagLayout());

		// adiciona nos paineis
		header.add(apresentacaoJLabel);

		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.weightx = 1;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(loginJLabel, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(senhaJLabel, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(loginJText, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(senhaJText, gBC);
		
		gBC.gridx = 0;
		gBC.gridy = 2;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(idioma, gBC);

		gBC.gridx = 1;
		gBC.gridy = 2;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(bt, gBC);

		// adiciona os paineis ao container
		container.add(BorderLayout.NORTH, header);
		container.add(BorderLayout.CENTER, content);

		application = new ReadLoginFile();
		application.openFile();

		// Registro no listener dos objetos controlados
		bt.addActionListener(this);

		// metodo que atualiza o texto de todos os componentes
		setComponentText();

		// Ajustes finais do frame
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(620, 450);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Implementacao do metodo da interface ActionListener
	public void actionPerformed(ActionEvent e)  {
		if (e.getSource() == bt)  {
			String login = loginJText.getText();
			String senha = new String(senhaJText.getPassword()).trim();
			
			// criptografa a senha informada
			
			// Instancia um objeto da classe CryptoAES
			CryptoAES caes = new CryptoAES();
			
			// senha em byte			
			byte[] bSenha = null;
			// senha criptografada em byte
			byte[] bSenhaCifrada = null;
			// senha cifrada Hexadecimal
			String sSenhaCifrada = "";
			try
			{
				// converte a senha digitada para byte
				bSenha = senha.getBytes("ISO-8859-1");
				File chave = new File("chave.simetrica");
				// gera a cifra da senha digitada
				caes.geraCifra(bSenha, chave);			
				// Recebe o texto cifrado
				bSenhaCifrada = caes.getTextoCifrado();
				//Converter senha cifrada para hexadecimal
				sSenhaCifrada = caes.fromHex(bSenhaCifrada);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			
			// verifica se o login e a senha est�o corretos						
			statusLogin = application.validaDadosLogin(login, sSenhaCifrada);
			application.closeFile();
			dispose();
		}
	}

	public void setComponentText() {
		bt.setText(bn.getString("telaAcessarCatraca.botao.entrar"));
		apresentacaoJLabel.setText(bn.getString("telaLogin.label.apresentacao"));
		loginJLabel.setText(bn.getString("telaAcessarCatraca.label.login"));
		senhaJLabel.setText(bn.getString("telaAcessarCatraca.label.senha"));

		setTitle(bn.getString("telaLogin.title"));
	}

	static boolean login(JFrame fr) {
		TelaLogin login = new TelaLogin(fr);
		return statusLogin;
	}

	public void setIdioma(int indice) {
		switch (indice) {
		case 0:
			bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));
			break;
		case 1:
			bn = ResourceBundle.getBundle("idioma", Locale.US);
			break;
		case 2:
			bn = ResourceBundle.getBundle("idioma", new Locale("es", "ES"));
			break;
		default:
			break;
		}
	}
}
