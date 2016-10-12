package br.com.saojudas.maven.projetointegrado.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import br.com.saojudas.maven.projetointegrado.components.TableModelAcesso;
import br.com.saojudas.maven.projetointegrado.components.TableModelEmpresa;
import br.com.saojudas.maven.projetointegrado.components.TableModelUsuario;

public class TelaConsultarAcesso extends JDialog implements ActionListener {
	// atributos para formulario
	private JButton bPesquisar, bLimpar, bConsultar;
	private JLabel lConsultarAcesso, lData, lEmpresa, lTipoDeUsuario;
	private JTextField tfRazaoSocial;
	private ButtonGroup bgTipoUsuario;
	private JRadioButton rbSindico, rbAtendente;
	private JFormattedTextField ftfData;

	// atributo mascara
	MaskFormatter mascaraData = null;

	// atributos para o menu
	private JMenu mArquivo, mEditar;
	private JMenuItem miSobre, miSair, miConsultar;
	private JMenuBar mbMenu;

	// atributos para �cones crud
	private ImageIcon iCreate, iRead, iUpdate;

	// atributo para manipulacao de idiomas
	private ResourceBundle bn = TelaPrincipal.bn;

	// atributos paineis
	private JPanel pHeader, pForm, pMain, pContent, pFooter;

	// atributos para tabela
	private JTable tTabela;
	private JScrollPane spEmpresa;
	private TableModelAcesso modelo;

	public TelaConsultarAcesso(JFrame fr) {
		// invoca o metodo construtor da superclasse
		super(fr, true);
		// determina o idioma selecionado
		// bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));

		Container container = getContentPane();// container
		container.setLayout(new BorderLayout());// instancia e atribui ao
		// layout border

		// instancia �cones
		iCreate = new ImageIcon("../image/ICONES-CRUD-02.jpg");
		iRead = new ImageIcon("../image/ICONES-CRUD-01.jpg");
		iUpdate = new ImageIcon("../image/ICONES-CRUD-03.jpg");

		// instancia botoes
		bPesquisar = new JButton();
		bLimpar = new JButton();
		bConsultar = new JButton("", iRead);

		// adiciona acao aos botoes
		bPesquisar.addActionListener(this);
		bLimpar.addActionListener(this);
		bConsultar.addActionListener(this);

		// instancia label
		lConsultarAcesso = new JLabel();
		lData = new JLabel();
		lEmpresa = new JLabel();
		lTipoDeUsuario = new JLabel();

		// radios buttons para tipos de usuario
		bgTipoUsuario = new ButtonGroup();
		rbSindico = new JRadioButton();
		rbAtendente = new JRadioButton();
		bgTipoUsuario.add(rbSindico);
		bgTipoUsuario.add(rbAtendente);

		// edicao de titulo
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lConsultarAcesso.setFont(fonteTitulo);
		lConsultarAcesso.setAlignmentX(CENTER_ALIGNMENT);
		lConsultarAcesso.setHorizontalAlignment(SwingConstants.CENTER);

		// instancia campo texto
		tfRazaoSocial = new JTextField(15);

		// instancia mascara
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
		} catch (Exception e) {
			System.err.println("Erro na formata��o: " + e.getMessage());
			System.exit(-1);
		}

		ftfData = new JFormattedTextField(mascaraData);

		// instancia menu
		mArquivo = new JMenu();
		mEditar = new JMenu();

		// instancia item menu
		miSobre = new JMenuItem();
		miSair = new JMenuItem();
		miConsultar = new JMenuItem();

		// instancia menu bar e configura no jframe
		mbMenu = new JMenuBar();
		setJMenuBar(mbMenu);

		// adiciona jmenu ao jmenubar
		mbMenu.add(mArquivo);
		mbMenu.add(mEditar);

		// adiciona os jmenuitens ao jmenu
		mArquivo.add(miSobre);
		mArquivo.add(miSair);
		mEditar.add(miConsultar);

		// instancia modelo
		modelo = new TableModelAcesso();
		String[] colunas = { bn.getString("telaConsulta.columnname.nome"), bn.getString("telaConsulta.columnname.cpf"),
				bn.getString("telaConsulta.columnname.empresa"), bn.getString("telaConsulta.columnname.data"),
				bn.getString("telaConsulta.columnname.entrada"), bn.getString("telaConsulta.columnname.saida") };
		modelo.setColunas(colunas);

		// instancia a table e atribui ao modelo criado
		tTabela = new JTable(modelo);
		tTabela.setFillsViewportHeight(true);

		// instancia RowSorter
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tTabela.setRowSorter(sorter);

		// instancia jscrollpane
		spEmpresa = new JScrollPane(tTabela);
		spEmpresa.setPreferredSize(new Dimension(650, 700));

		// instancia paineis
		pForm = new JPanel();
		pMain = new JPanel();
		pMain.setBorder(new EmptyBorder(10, 10, 10, 10));
		pContent = new JPanel();
		pHeader = new JPanel();
		pHeader.setBorder(new EmptyBorder(10, 10, 10, 10));
		pFooter = new JPanel();

		// instancia borda para o painel de formulario
		pForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));

		// atribui layout para cada painel
		pForm.setLayout(new GridBagLayout());
		pMain.setLayout(new GridLayout(2, 1, 5, 5));
		pContent.setLayout(new GridLayout(1, 1, 5, 5));
		pHeader.setLayout(new GridLayout(1, 1, 5, 5));
		pFooter.setLayout(new GridLayout(1, 1, 5, 5));

		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(lData, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.weightx = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(ftfData, gBC);

		gBC.gridx = 2;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(lEmpresa, gBC);

		gBC.gridx = 3;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.weightx = 4;
		pForm.add(tfRazaoSocial, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		pForm.add(lTipoDeUsuario, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		pForm.add(rbSindico, gBC);

		gBC.gridx = 1;
		gBC.gridy = 2;
		pForm.add(rbAtendente, gBC);

		gBC.gridx = 0;
		gBC.gridy = 3;
		gBC.insets = new Insets(10, 5, 5, 5);
		pForm.add(bPesquisar, gBC);

		gBC.gridx = 1;
		gBC.gridy = 3;
		gBC.insets = new Insets(10, 5, 5, 5);
		pForm.add(bLimpar, gBC);

		gBC.gridx = 3;
		gBC.gridy = 3;
		gBC.insets = new Insets(15, 5, 5, 5);
		pForm.add(bConsultar, gBC);

		// adiciona elementos ao painel
		pHeader.add(lConsultarAcesso);
		pMain.add(pForm);
		pMain.add(pContent);
		pContent.add(spEmpresa);

		// adiciona os paineis ao container
		container.add(BorderLayout.NORTH, pHeader);
		container.add(BorderLayout.CENTER, pMain);
		container.add(BorderLayout.SOUTH, pFooter);

		// metodo que atualiza o texto de todos os componentes
		setComponentText();

		// congifura o painel
		setSize(650, 450);
		setLocation(350, 80);
		setVisible(true);
		// setExtendedState(MAXIMIZED_BOTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bPesquisar) {

		}
		if (e.getSource() == bLimpar) {

		}
	}

	public void setComponentText() {
		bPesquisar.setText(bn.getString("telaConsultarAcesso.botao.pesquisar"));
		bLimpar.setText(bn.getString("telaConsultarAcesso.botao.limpar"));
		bConsultar.setText(bn.getString("telaConsultarAcesso.botao.consultar"));

		lConsultarAcesso.setText(bn.getString("telaConsultarAcesso.label.consultaracesso"));
		lData.setText(bn.getString("telaConsultarAcesso.label.data"));
		lEmpresa.setText(bn.getString("telaConsultarAcesso.label.empresa"));
		lTipoDeUsuario.setText(bn.getString("telaConsultarAcesso.label.tipodeusuario"));
		rbSindico.setText(bn.getString("telaCadastrarUsuario.radioButton.sindico"));
		rbAtendente.setText(bn.getString("telaCadastrarUsuario.radioButton.atendente"));

		mArquivo.setText(bn.getString("telaPrincipal.menu.arquivo"));
		mEditar.setText(bn.getString("telaConsulta.menu.editar"));

		miSobre.setText(bn.getString("telaPrincipal.menuitem.sobre"));
		miSair.setText(bn.getString("telaPrincipal.menuitem.sair"));
		miConsultar.setText(bn.getString("telaConsulta.menuitem.consultar"));

		setTitle(bn.getString("telaConsultarAcesso.title"));
	}
}
