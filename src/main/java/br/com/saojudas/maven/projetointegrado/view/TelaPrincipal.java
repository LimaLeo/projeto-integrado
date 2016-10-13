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
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame implements ActionListener {
	// atributos para botoes
	private JButton bConsultarEmpresa, bManterUsuario, bEnviarArquivoDeAcesso, bEnviarReconfiguracaoDeTemperatura,
			bAcessarCatraca, bConsultarAcesso;
	private JLabel lBoasVidas, lAdministracao, lEnviarArquivoDeAcesso, lEnviarReconfiguracaoDeTemperatura;

	// atributos para o menu
	private JMenu mArquivo, mIdioma;
	private JMenuItem miSobre, miSair, miPortugues, miIngles, miEspanhol, miEmpresa, miUsuario;
	private JMenuBar mbMenu;

	// atributos abas
	private JTabbedPane abas;

	// atributo para manipulacao de idiomas
	public static ResourceBundle bn = null;

	// atributos paineis
	private JPanel pMenusSistemaControlePredial, pMain, pMenuSistemaDeCatraca, pHeader, pFooter, pConfiguracoes;

	// tela de login
	private TelaLogin telaLogin;

	public TelaPrincipal() {
		// determina o idioma padrao para portugues
		bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));

		Container container = getContentPane();// container

		container.setLayout(new BorderLayout());// instancia e atribui ao
		// layout border
		
		AplicaLookAndFeel.lookAndFeel();

		// instancia abas
		abas = new JTabbedPane();

		// instancia botoes
		bConsultarEmpresa = new JButton();
		bManterUsuario = new JButton();
		bEnviarArquivoDeAcesso = new JButton();
		bEnviarReconfiguracaoDeTemperatura = new JButton();
		bAcessarCatraca = new JButton();
		bConsultarAcesso = new JButton();

		// determina o tamanho dos botoes
		bConsultarEmpresa.setSize(50, 200);
		bManterUsuario.setSize(50, 200);
		bEnviarArquivoDeAcesso.setSize(50, 200);
		bEnviarReconfiguracaoDeTemperatura.setSize(50, 200);
		bAcessarCatraca.setSize(50, 200);
		bConsultarAcesso.setSize(50, 200);

		// adiciona acao aos botoes
		bConsultarEmpresa.addActionListener(this);
		bManterUsuario.addActionListener(this);
		bEnviarArquivoDeAcesso.addActionListener(this);
		bEnviarReconfiguracaoDeTemperatura.addActionListener(this);
		bAcessarCatraca.addActionListener(this);
		bConsultarAcesso.addActionListener(this);

		// instancia label
		lBoasVidas = new JLabel("", SwingConstants.LEFT);
		lAdministracao = new JLabel();
		lEnviarArquivoDeAcesso = new JLabel();
		lEnviarReconfiguracaoDeTemperatura = new JLabel();

		// edicao de titulo
		lBoasVidas.setAlignmentX(RIGHT_ALIGNMENT);
		lBoasVidas.setHorizontalAlignment(SwingConstants.RIGHT);

		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lAdministracao.setFont(fonteTitulo);
		lAdministracao.setAlignmentX(CENTER_ALIGNMENT);
		lAdministracao.setHorizontalAlignment(SwingConstants.CENTER);

		// instancia menu
		mArquivo = new JMenu();
		mIdioma = new JMenu();

		// instancia item menu
		miSobre = new JMenuItem();
		miSair = new JMenuItem();
		miPortugues = new JMenuItem();
		miIngles = new JMenuItem();
		miEspanhol = new JMenuItem();
		miEmpresa = new JMenuItem();
		miUsuario = new JMenuItem();

		// instancia menu bar e configura no jframe
		mbMenu = new JMenuBar();
		setJMenuBar(mbMenu);

		// adiciona jmenu ao jmenubar
		mbMenu.add(mArquivo);
		mbMenu.add(mIdioma);

		// adiciona os jmenuitens ao jmenu
		mArquivo.add(miSobre);
		mArquivo.add(miSair);
		mIdioma.add(miPortugues);
		mIdioma.add(miIngles);
		mIdioma.add(miEspanhol);

		// Adicionar os metodos dos MenuItens
		miPortugues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == miPortugues) {
					// determina o idioma para portugues
					bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));
					setComponentText();
				}

			}
		});

		miIngles.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == miIngles) {
					// determina o idioma para ingles
					bn = ResourceBundle.getBundle("idioma", Locale.US);
					setComponentText();
				}
			}

		});

		miEspanhol.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == miEspanhol) {
					// determina o idioma para ingles
					bn = ResourceBundle.getBundle("idioma", new Locale("es", "ES"));
					setComponentText();
				}

			}
		});

		// instancia paineis
		pMenusSistemaControlePredial = new JPanel();
		pMain = new JPanel();
		pMenuSistemaDeCatraca = new JPanel();
		pHeader = new JPanel();
		pFooter = new JPanel();
		pConfiguracoes = new JPanel();

		// atribui layout para cada painel
		pMenusSistemaControlePredial.setLayout(new GridLayout(5, 1, 5, 5));
		pMain.setLayout(new GridLayout(1, 1, 5, 5));
		pMenuSistemaDeCatraca.setLayout(new GridLayout(5, 1, 5, 5));
		pHeader.setLayout(new GridBagLayout());
		pFooter.setLayout(new GridLayout(1, 1, 5, 5));
		pConfiguracoes.setLayout(new GridLayout(6, 1, 10, 10));

		// adiciona elementos ao painel bAcessarCatraca, bConsultarAcesso
		pMain.add(abas);
		pMenusSistemaControlePredial.add(bConsultarEmpresa);
		pMenusSistemaControlePredial.add(bManterUsuario);
		pMenusSistemaControlePredial.setBorder(new EmptyBorder(10, 10, 10, 450));

		pMenuSistemaDeCatraca.add(bAcessarCatraca);
		pMenuSistemaDeCatraca.add(bConsultarAcesso);
		pMenuSistemaDeCatraca.setBorder(new EmptyBorder(10, 10, 10, 450));

		pConfiguracoes.add(lEnviarArquivoDeAcesso);
		pConfiguracoes.add(bEnviarArquivoDeAcesso);
		pConfiguracoes.add(lEnviarReconfiguracaoDeTemperatura);
		pConfiguracoes.add(bEnviarReconfiguracaoDeTemperatura);
		pConfiguracoes.setBorder(new EmptyBorder(10, 10, 10, 100));

		// adiciona elementos nas abas
		abas.addTab("", pMenusSistemaControlePredial);
		abas.addTab("", pMenuSistemaDeCatraca);
		abas.addTab("", pConfiguracoes);

		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pHeader.add(lAdministracao, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.weightx = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pHeader.add(lBoasVidas, gBC);

		// adiciona os paineis ao container
		container.add(BorderLayout.NORTH, pHeader);
		container.add(BorderLayout.CENTER, pMain);
		container.add(BorderLayout.SOUTH, pFooter);

		// congifura o painel
		setVisible(true);
		setSize(650, 550);
		setLocation(350, 80);

		// edicao background
		// getContentPane().setBackground(new Color(152, 251, 152));

		// setExtendedState(MAXIMIZED_BOTH);

		// metodo que atualiza o texto de todos os componentes
		setComponentText();

		// metodo para realizacao de login
		boolean statusLogin;
		do {
			statusLogin = TelaLogin.login(this);
		} while (!statusLogin);

		// metodo que atualiza o texto de todos os componentes
		bn = TelaLogin.bn;
		setComponentText();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bConsultarEmpresa) {
			TelaConsultarEmpresa consultarEmpresa = new TelaConsultarEmpresa();
		}

		if (e.getSource() == bManterUsuario) {
			TelaConsultarUsuario consultarUsuario = new TelaConsultarUsuario();
		}

		if (e.getSource() == bEnviarArquivoDeAcesso) {

		}

		if (e.getSource() == bAcessarCatraca) {
			TelaAcessarCatraca acessarCatraca = new TelaAcessarCatraca(this);
			
		}
		if (e.getSource() == bConsultarAcesso) {
			TelaConsultarAcesso telaConsultarAcesso = new TelaConsultarAcesso(this);
		}
	}

	public void setComponentText() {
		abas.setTitleAt(0, bn.getString("telaPrincipal.abas.sistemacontrolepredial"));
		abas.setTitleAt(1, bn.getString("telaPrincipal.abas.sistemacatraca"));
		abas.setTitleAt(2, bn.getString("telaPrincipal.abas.configuracao"));

		bConsultarEmpresa.setText(bn.getString("telaPrincipal.botao.consultarempresa"));
		bManterUsuario.setText(bn.getString("telaPrincipal.botao.consultarusuario"));
		bEnviarArquivoDeAcesso.setText(bn.getString("telaPrincipal.botao.configuracoes"));
		bEnviarReconfiguracaoDeTemperatura.setText(bn.getString("telaPrincipal.botao.reconfiguracaodetemperatura"));
		bAcessarCatraca.setText(bn.getString("telaPrincipal.botao.acessarcatraca"));
		bConsultarAcesso.setText(bn.getString("telaPrincipal.botao.consultaracesso"));

		lBoasVidas.setText(bn.getString("telaPrincipal.label.boasvidas"));
		lAdministracao.setText(bn.getString("telaPrincipal.label.gerenciador"));
		lEnviarArquivoDeAcesso.setText(bn.getString("telaPrincipal.label.arquivodeacesso"));
		lEnviarReconfiguracaoDeTemperatura.setText(bn.getString("telaPrincipal.label.reconfiguracaotemperatura"));

		mArquivo.setText(bn.getString("telaPrincipal.menu.arquivo"));
		mIdioma.setText(bn.getString("telaPrincipal.menu.idioma"));

		miSobre.setText(bn.getString("telaPrincipal.menuitem.sobre"));
		miSair.setText(bn.getString("telaPrincipal.menuitem.sair"));
		miPortugues.setText(bn.getString("telaPrincipal.menuitem.portugues"));
		miIngles.setText(bn.getString("telaPrincipal.menuitem.ingles"));
		miEspanhol.setText(bn.getString("telaPrincipal.menuitem.espanhol"));
		miEmpresa.setText(bn.getString("telaPrincipal.menuitem.empresa"));
		miUsuario.setText(bn.getString("telaPrincipal.menuitem.usuario"));

		// atualiza title
		setTitle(bn.getString("telaPrincipal.title"));
	}
}
