package br.com.saojudas.maven.projetointegrado.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.saojudas.maven.projetointegrado.components.CreateLoginFile;
import br.com.saojudas.maven.projetointegrado.components.CryptoAES;
import br.com.saojudas.maven.projetointegrado.control.EmpresaCtrl;
import br.com.saojudas.maven.projetointegrado.dao.EmpresaDao;
import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.TipoUsuario;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class TelaCadastrarUsuario extends JDialog implements ActionListener {
	// componentes formul�rio
	private JLabel lTitulo, lTipoUsuario, lNome, lLogin, lCpf, lSenha, lHorario, lCnpj, lPermissao, lLivreAcesso;
	private JTextField tfNome, tfLogin;
	private JPasswordField pfSenha;
	private ButtonGroup bgTipoUsuario, bgPermissao, bgLivreAcesso;
	private JRadioButton rbSindico, rbAtendente, rbFuncionario, rbSimPerm, rbNaoPerm, rbSimLivre, rbNaoLivre;
	private JButton bSalvar, bLimpar;
	private JFormattedTextField ftfCpf, ftfCnpj, ftfHorario;

	// atributo de empresa dao
	EmpresaDao empresaDao;
	Empresa empresa;

	// atributo mascara
	MaskFormatter mascaraCpf = null, mascaraCnpj = null, mascaraHorario;

	// atributos paineis
	private JPanel pNorte, pCentro, pSul, pDadosUsuario, pDadosLogin;

	// atributo JComboBox
	private JComboBox<String> cbEmpresas;
	// Container
	Container container;

	// atributo para manipulacao de idiomas
	private ResourceBundle bn = TelaPrincipal.bn;

	// atributo componente de criacao de login
	private CreateLoginFile application;

	// atributo usuario
	private static Usuario usuario;

	private List<Empresa> empresas;
	private EmpresaCtrl empresaCtrl;

	// atributo status da tela
	static EstadoTela estadoTela;

	public TelaCadastrarUsuario(JFrame fr, Usuario usuario) {
		// invoca o metodo construtor da superclasse
		super(fr, true);

		// determina o idioma padrao para portugues
		// bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));

		// configura o Container
		container = getContentPane();
		container.setLayout(new BorderLayout(20, 20));

		AplicaLookAndFeel.lookAndFeel();

		// Instancia os componentes do Sul
		bSalvar = new JButton();
		getRootPane().setDefaultButton(bSalvar);

		bLimpar = new JButton();

		bSalvar.addActionListener(this);
		bLimpar.addActionListener(this);

		// atributo configura o componente Titulo
		lTitulo = new JLabel();
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lTitulo.setFont(fonteTitulo);
		lTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		// Instancia os componentes do Centro
		lTipoUsuario = new JLabel();
		lNome = new JLabel();
		lLogin = new JLabel();
		lCpf = new JLabel();
		lSenha = new JLabel();
		lHorario = new JLabel();
		lCnpj = new JLabel();
		lPermissao = new JLabel();
		lLivreAcesso = new JLabel();

		cbEmpresas = new JComboBox<String>();

		// instancia empresas
		empresaCtrl = new EmpresaCtrl();
		empresas = empresaCtrl.consultarTodasEmpresas();

		for (Empresa e : empresas) {
			cbEmpresas.addItem(e.getRazaoSocial());
		}

		bgTipoUsuario = new ButtonGroup();
		rbSindico = new JRadioButton();
		rbAtendente = new JRadioButton();
		rbFuncionario = new JRadioButton();
		bgTipoUsuario.add(rbSindico);
		bgTipoUsuario.add(rbAtendente);
		bgTipoUsuario.add(rbFuncionario);
		rbSindico.setSelected(true);

		bgPermissao = new ButtonGroup();
		rbSimPerm = new JRadioButton();
		rbNaoPerm = new JRadioButton();
		bgPermissao.add(rbSimPerm);
		bgPermissao.add(rbNaoPerm);
		rbNaoPerm.setSelected(true);

		bgLivreAcesso = new ButtonGroup();
		rbSimLivre = new JRadioButton();
		rbNaoLivre = new JRadioButton();
		bgLivreAcesso.add(rbSimLivre);
		bgLivreAcesso.add(rbNaoLivre);
		rbNaoLivre.setSelected(true);

		tfNome = new JTextField(15);
		tfLogin = new JTextField(15);
		pfSenha = new JPasswordField(15);
		ftfHorario = new JFormattedTextField();

		// instancia mascara
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');

			mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			mascaraCnpj.setPlaceholderCharacter('_');

			mascaraHorario = new MaskFormatter("##:## a ##:##");
			mascaraHorario.setPlaceholderCharacter('_');
		} catch (Exception e) {
			System.err.println("Erro na formatacao" + e.getMessage());
			System.exit(-1);
		}

		ftfCpf = new JFormattedTextField(mascaraCpf);
		ftfCnpj = new JFormattedTextField(mascaraCnpj);
		ftfHorario = new JFormattedTextField(mascaraHorario);

		// instancia paineis
		pNorte = new JPanel(new GridLayout(1, 1, 5, 5));
		pCentro = new JPanel(new BorderLayout());
		pSul = new JPanel(new GridBagLayout());
		pDadosUsuario = new JPanel(new GridBagLayout());
		pDadosLogin = new JPanel(new GridBagLayout());

		// instancia borda para o painel de formulario
		pDadosUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do usu�rio"));
		pDadosLogin.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados de login"));
		pCentro.setBorder(new EmptyBorder(10, 10, 10, 10));
		pNorte.setBorder(new EmptyBorder(10, 10, 10, 10));
		pSul.setBorder(new EmptyBorder(10, 10, 10, 10));

		// adiciona Componentes aos Panels
		pNorte.add(lTitulo);
		pCentro.add(BorderLayout.NORTH, pDadosUsuario);
		pCentro.add(BorderLayout.CENTER, pDadosLogin);

		// instancia empresa dao
		empresaDao = new EmpresaDao();
		empresa = new Empresa();

		// instancia componente de criacao de login
		application = new CreateLoginFile();

		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.weightx = 1;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(lTipoUsuario, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.insets = new Insets(1, 1, 1, 1);
		pDadosUsuario.add(rbSindico, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		gBC.insets = new Insets(1, 1, 1, 1);
		pDadosUsuario.add(rbAtendente, gBC);

		gBC.gridx = 1;
		gBC.gridy = 2;
		gBC.insets = new Insets(1, 1, 1, 1);
		pDadosUsuario.add(rbFuncionario, gBC);

		gBC.gridx = 0;
		gBC.gridy = 3;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(lNome, gBC);

		gBC.gridx = 1;
		gBC.gridy = 3;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(tfNome, gBC);

		gBC.gridx = 0;
		gBC.gridy = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(lCpf, gBC);

		gBC.gridx = 1;
		gBC.gridy = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(ftfCpf, gBC);

		// gBC.gridx = 0;
		// gBC.gridy = 5;
		// gBC.insets = new Insets(5, 5, 5, 5);
		// pDadosUsuario.add(lSenha, gBC);
		//
		// gBC.gridx = 1;
		// gBC.gridy = 5;
		// gBC.insets = new Insets(5, 5, 5, 5);
		// pDadosUsuario.add(pfSenha, gBC);

		gBC.gridx = 0;
		gBC.gridy = 6;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(lHorario, gBC);

		gBC.gridx = 1;
		gBC.gridy = 6;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(ftfHorario, gBC);

		gBC.gridx = 0;
		gBC.gridy = 7;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(lCnpj, gBC);

		gBC.gridx = 1;
		gBC.gridy = 7;
		gBC.insets = new Insets(5, 5, 5, 5);
		// pDadosUsuario.add(cbEmpresas, gBC);
		pDadosUsuario.add(ftfCnpj, gBC);

		gBC.gridx = 0;
		gBC.gridy = 8;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosUsuario.add(lPermissao, gBC);

		gBC.gridx = 1;
		gBC.gridy = 8;
		gBC.insets = new Insets(0, 0, 0, 0);
		pDadosUsuario.add(rbSimPerm, gBC);

		gBC.gridx = 1;
		gBC.gridy = 9;
		gBC.insets = new Insets(0, 0, 0, 0);
		pDadosUsuario.add(rbNaoPerm, gBC);

		gBC.gridx = 0;
		gBC.gridy = 10;
		gBC.insets = new Insets(20, 5, 5, 5);
		pDadosUsuario.add(lLivreAcesso, gBC);

		gBC.gridx = 1;
		gBC.gridy = 10;
		gBC.insets = new Insets(20, 0, 0, 0);
		pDadosUsuario.add(rbSimLivre, gBC);

		gBC.gridx = 1;
		gBC.gridy = 11;
		gBC.insets = new Insets(0, 0, 0, 0);
		pDadosUsuario.add(rbNaoLivre, gBC);

		gBC.weightx = 0;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(bSalvar, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(bLimpar, gBC);

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosLogin.add(lLogin, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosLogin.add(tfLogin, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosLogin.add(lSenha, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pDadosLogin.add(pfSenha, gBC);

		// adiciona os paineis ao container
		container.add(BorderLayout.NORTH, pNorte);
		container.add(BorderLayout.CENTER, pCentro);
		container.add(BorderLayout.SOUTH, pSul);

		estadoTela = TelaConsultarUsuario.alteraEstadoTela;

		System.out.println(estadoTela);

		alteraTipoDeTela(usuario);

		// metodo que atualiza o texto de todos os componentes
		setComponentText();

		// congifura o painel
		setSize(650, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		// setExtendedState(MAXIMIZED_BOTH);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bSalvar) {
			if (estadoTela == EstadoTela.CADASTRAR) {
				try {
					TipoUsuario tipoUsuario = TipoUsuario.SINDICO;
					if (rbAtendente.isSelected()) {
						tipoUsuario = TipoUsuario.ATENDENTE;
					}
					if (rbFuncionario.isSelected()) {
						tipoUsuario = TipoUsuario.FUNCIONARIO;
					}

					String senha = new String(pfSenha.getPassword()).trim();
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

					// busca a empresa do usuario
					// empresa =
					// empresaCtrl.consultaEmpresa(empresas.get(cbEmpresas.getSelectedIndex()).getCnpj());

					empresa = empresaCtrl.consultaEmpresa(
							ftfCnpj.getText().replace("_", "").replace(".", "").replace("-", "").replace("/", ""));
					// instancia objeto usuario
					usuario = new Usuario(tfNome.getText(),
							ftfCpf.getText().replace("_", "").replace(".", "").replace("-", ""), tfLogin.getText(),
							sSenhaCifrada, ftfHorario.getText().replace("_", ""), tipoUsuario,
							(rbSimLivre.isSelected()) ? true : false, (rbSimPerm.isSelected()) ? true : false, empresa);

					dispose();

				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			} else if (estadoTela == EstadoTela.ALTERAR) {
				try {
					TipoUsuario tipoUsuario = TipoUsuario.SINDICO;
					if (rbAtendente.isSelected()) {
						tipoUsuario = TipoUsuario.ATENDENTE;
					}
					if (rbFuncionario.isSelected()) {
						tipoUsuario = TipoUsuario.FUNCIONARIO;
					}

					String senha = new String(pfSenha.getPassword()).trim();
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

					empresa = empresaCtrl.consultaEmpresa(empresas.get(cbEmpresas.getSelectedIndex()).getCnpj());

					// empresa = empresaDao.consultaEmpresa(
					// ftfCnpj.getText().replace("_", "").replace(".",
					// "").replace("-", "").replace("/", ""));

					usuario = new Usuario(tfNome.getText(),
							ftfCpf.getText().replace("_", "").replace(".", "").replace("-", ""), tfLogin.getText(),
							sSenhaCifrada, ftfHorario.getText().replace("_", ""), tipoUsuario,
							(rbSimLivre.isSelected()) ? true : false, (rbSimPerm.isSelected()) ? true : false, empresa);

					dispose();
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (e.getSource() == bLimpar) {
			rbSindico.setSelected(true);
			rbNaoPerm.setSelected(true);
			rbNaoLivre.setSelected(true);
			tfNome.setText("");
			tfLogin.setText("");
			pfSenha.setText("");
			ftfCpf.setText("");
			ftfCnpj.setText("");
			ftfHorario.setText("");
		}
	}

	public void setComponentText() {
		lTitulo.setText(bn.getString("telaCadastrarUsuario.label.titulo"));

		lTipoUsuario.setText(bn.getString("telaCadastrarUsuario.label.tipoUsuario"));
		lNome.setText(bn.getString("telaCadastrarUsuario.label.nome"));
		lLogin.setText(bn.getString("telaCadastrarUsuario.label.login"));
		lCpf.setText(bn.getString("telaCadastrarUsuario.label.cpf"));
		lSenha.setText(bn.getString("telaCadastrarUsuario.label.senha"));
		lHorario.setText(bn.getString("telaCadastrarUsuario.label.horario"));
		lCnpj.setText(bn.getString("telaCadastrarUsuario.label.cnpj"));
		lPermissao.setText(bn.getString("telaCadastrarUsuario.label.permissao"));
		lLivreAcesso.setText(bn.getString("telaCadastrarUsuario.label.livre"));

		rbSindico.setText(bn.getString("telaCadastrarUsuario.radioButton.sindico"));
		rbAtendente.setText(bn.getString("telaCadastrarUsuario.radioButton.atendente"));
		rbFuncionario.setText(bn.getString("telaCadastrarUsuario.radioButton.funcionario"));
		rbSimPerm.setText(bn.getString("telaCadastrarUsuario.radioButton.sim"));
		rbNaoPerm.setText(bn.getString("telaCadastrarUsuario.radioButton.nao"));
		rbSimLivre.setText(bn.getString("telaCadastrarUsuario.radioButton.sim"));
		rbNaoLivre.setText(bn.getString("telaCadastrarUsuario.radioButton.nao"));

		bSalvar.setText(bn.getString("telaCadastrarUsuario.button.salvar"));
		bLimpar.setText(bn.getString("telaCadastrarUsuario.button.limpar"));
		setTitle(bn.getString("telaCadastrarUsuario.label.titulo"));

	}

	static Usuario cadastrarUsuario(JFrame fr) {
		TelaCadastrarUsuario cadastrarUsuario = new TelaCadastrarUsuario(fr, null);
		return usuario;
	}

	static Usuario alteraUsuario(JFrame fr, Usuario usuarioAlterado) {
		TelaCadastrarUsuario cadastrarUsuario = new TelaCadastrarUsuario(fr, usuarioAlterado);
		return usuario;
	}

	public void alteraTipoDeTela(Usuario usuario) {
		if (estadoTela == EstadoTela.CONSULTAR) {
			tfNome.setText(usuario.getNome());
			tfLogin.setText(usuario.getLogin());
			pfSenha.setText(usuario.getSenha());
			ftfCpf.setText(usuario.getCpf());
			if (usuario.getEmpresa() != null) {
				ftfCnpj.setText(usuario.getEmpresa().getCnpj());
			}
			ftfHorario.setText(usuario.getHorarioDeAcesso());

			// desativa a edição
			bSalvar.setEnabled(false);
			bLimpar.setEnabled(false);
			rbSindico.setEnabled(false);
			rbAtendente.setEnabled(false);
			rbFuncionario.setEnabled(false);
			rbNaoPerm.setEnabled(false);
			rbSimPerm.setEnabled(false);
			rbNaoLivre.setEnabled(false);
			rbSimLivre.setEnabled(false);
			tfNome.setEnabled(false);
			tfLogin.setEnabled(false);
			pfSenha.setEnabled(false);
			ftfCpf.setEnabled(false);
			ftfCnpj.setEnabled(false);
			ftfHorario.setEnabled(false);
		} else if (estadoTela == EstadoTela.CADASTRAR) {

		} else {
			tfNome.setText(usuario.getNome());
			tfLogin.setText(usuario.getLogin());
			pfSenha.setText(usuario.getSenha());
			ftfCpf.setText(usuario.getCpf());
			if (usuario.getEmpresa() != null) {
				ftfCnpj.setText(usuario.getEmpresa().getCnpj());
			}
			ftfHorario.setText(usuario.getHorarioDeAcesso());

			// desativa a edição
			bSalvar.setEnabled(true);
			bLimpar.setEnabled(true);
			rbSindico.setEnabled(true);
			rbAtendente.setEnabled(true);
			rbFuncionario.setEnabled(true);
			rbNaoPerm.setEnabled(true);
			rbSimPerm.setEnabled(true);
			rbNaoLivre.setEnabled(true);
			rbSimLivre.setEnabled(true);
			tfNome.setEnabled(true);
			tfLogin.setEnabled(true);
			pfSenha.setEnabled(true);
			ftfCpf.setEnabled(true);
			ftfCnpj.setEnabled(true);
			ftfHorario.setEnabled(true);
		}
	}

}
