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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.saojudas.maven.projetointegrado.components.CryptoAES;
import br.com.saojudas.maven.projetointegrado.components.ReadLoginFile;
import br.com.saojudas.maven.projetointegrado.control.AcessoCtrl;
import br.com.saojudas.maven.projetointegrado.control.UsuarioCtrl;
import br.com.saojudas.maven.projetointegrado.model.Acesso;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class TelaAcessarCatraca extends JDialog implements ActionListener {

	private ResourceBundle bn = TelaPrincipal.bn;

	// atributos para formulario
	private JButton bEntrar, bCancelar;
	private JTextField loginJText;
	private JPasswordField senhaJText;
	private JLabel loginJLabel, senhaJLabel, apresentacaoJLabel;

	// atributos paineis
	private JPanel header, content;

	// atributo de login
	private ReadLoginFile application;

	// modo da tela, 1 - Entrar, 2 - Sair
	private int modo;

	public TelaAcessarCatraca(JFrame fr, int modo) {
		// invoca o m�todo construtor da superclasse
		super(fr, true);
		this.modo = modo;

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		// AplicaLookAndFeel.lookAndFeel();

		// instancia itens de formulario
		bEntrar = new JButton();
		getRootPane().setDefaultButton(bEntrar);

		bCancelar = new JButton();

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

		gBC.gridx = 1;
		gBC.gridy = 2;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(bEntrar, gBC);

		gBC.gridx = 0;
		gBC.gridy = 2;
		gBC.insets = new Insets(5, 5, 5, 5);
		content.add(bCancelar, gBC);

		// adiciona os paineis ao container
		container.add(BorderLayout.NORTH, header);
		container.add(BorderLayout.CENTER, content);

		// Registro no listener dos objetos controlados
		bEntrar.addActionListener(this);
		bCancelar.addActionListener(this);

		// M�todo que atualiza o texto de todos os componentes
		setComponentText();

		// Ajustes finais do frame
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(400, 250);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	// Implementacao do metodo da interface ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bEntrar) {
			application = new ReadLoginFile();
			application.openFile();

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
			try {
				// converte a senha digitada para byte
				bSenha = senha.getBytes("ISO-8859-1");
				File chave = new File("chave.simetrica");
				// gera a cifra da senha digitada
				caes.geraCifra(bSenha, chave);
				// Recebe o texto cifrado
				bSenhaCifrada = caes.getTextoCifrado();
				// Converter senha cifrada para hexadecimal
				sSenhaCifrada = caes.fromHex(bSenhaCifrada);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// verifica se o login e a senha est�o corretos
			if (application.validaDadosLogin(login, sSenhaCifrada)) {
				application.closeFile();

				// Inserir dados de acesso no Banco
				// Buscar usuario no banco
				UsuarioCtrl usuarioCtrl = new UsuarioCtrl();
				Usuario usuario = usuarioCtrl.consultaUsuarioLogin(login);

				// VERIFICA SE O USUARIO TEM ACESSO NESTE HORARIO
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				LocalTime timeAtual = LocalTime.parse(dateFormat.format(new Date()));

				// Cria o acesso
				Acesso acesso;
				AcessoCtrl acessoCtrl = new AcessoCtrl();

				// busca no banco se o usuario entrou e ainda nao saiu
				acesso = acessoCtrl.consultarAcesso(usuario);
				try {
					// usuario entrou e nao saiu
					String cpf = acesso.getUsuario().getCpf();
				} catch (Exception e2) {
					// usuario nao entrou
					acesso = null;
				}

				// Verifica se esta entrando ou saindo da catraca
				if (modo == 1) // Entrando catraca
				{
					// Verifica se o usuario ainda nao saiu pela catraca
					if (acesso == null) {
						LocalTime horaEntrada = LocalTime.parse(usuario.getHorarioDeAcesso().substring(0, 5));
						LocalTime horaSaida = LocalTime.parse(usuario.getHorarioDeAcesso().substring(8, 13));

						// Compara o horario de acesso, com o horario atual
						if (timeAtual.isAfter(horaEntrada) && timeAtual.isBefore(horaSaida)) {
							// adiciona o usuario ao acesso
							acesso = new Acesso();
							acesso.setUsuario(usuario);
							acesso.setEntrada(new Date());

							// Cadastra no Banco
							acessoCtrl.incluirAcesso(acesso);

							JOptionPane.showMessageDialog(null, bn.getString("readLoginFile.message.loginRealizado"));
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Horario de acesso não permitido!");
							// Limpa
							loginJText.setText("");
							senhaJText.setText("");
						}
					} else // Usuario ja entrou e ainda nao saiu
					{
						JOptionPane.showMessageDialog(null,
								"Usuário já passou pela catraca e ainda não saiu!\nEntrada Bloqueada!");
						// Limpa
						loginJText.setText("");
						senhaJText.setText("");
					}
				} else // saindo catraca
				{
					// Usuario entrou e nao saiu
					if (acesso != null) {
						acesso.setSaida(new Date());
						acessoCtrl.alterarAcesso(acesso.getId(), acesso);
						JOptionPane.showMessageDialog(null, "Saída liberada!");

						dispose();
					} else // usuario nao entrou, portanto nao pode sair
					{
						JOptionPane.showMessageDialog(null, "Usuario não entrou! Saída bloqueada!");
						// Limpa
						loginJText.setText("");
						senhaJText.setText("");
					}
				}
			} else {
				loginJText.setText("");
				senhaJText.setText("");

				application.closeFile();

				JOptionPane.showMessageDialog(null, bn.getString("readLoginFile.message.loginNaoRealizado"));
			}

		} else if (e.getSource() == bCancelar) {
			dispose();
		}
	}

	public void setComponentText() {
		if (modo == 1) {
			bEntrar.setText(bn.getString("telaAcessarCatraca.botao.entrar"));
		} else {
			bEntrar.setText("Saída");
		}

		apresentacaoJLabel.setText(bn.getString("telaAcessarCatraca.label.apresentacao"));
		loginJLabel.setText(bn.getString("telaAcessarCatraca.label.login"));
		senhaJLabel.setText(bn.getString("telaAcessarCatraca.label.senha"));
		bCancelar.setText(bn.getString("telaAcessarCatraca.botao.cancelar"));

		setTitle(bn.getString("telaAcessarCatraca.title"));
	}

}
