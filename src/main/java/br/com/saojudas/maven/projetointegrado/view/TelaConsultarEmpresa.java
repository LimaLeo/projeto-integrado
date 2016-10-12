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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import br.com.saojudas.maven.projetointegrado.components.TableModelEmpresa;

public class TelaConsultarEmpresa extends JFrame implements ActionListener {
	// atributos para formulario
	private JButton bPesquisar, bLimpar, bCadastrar, bAlterar, bColsultar;
	private JLabel lConsultarEmpresa, lCnpj, lRazaoSocial;
	private JTextField tfRazaoSocial;
	private JFormattedTextField ftfCnpj;

	// atributo mascara
	MaskFormatter mascaraCnpj = null;

	// atributos para o menu
	private JMenu mArquivo, mEditar;
	private JMenuItem miSobre, miSair, miConsultar, miAlterar, miNovoCadastro;
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
	private TableModelEmpresa modelo;

	public TelaConsultarEmpresa() {
		// determina o idioma padrao para portugues
		// bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));

		Container container = getContentPane();// container
		container.setLayout(new BorderLayout());// instancia e atribui ao
		// layout border

		// instancia icones
		iCreate = new ImageIcon("../image/ICONES-CRUD-02.jpg");
		iRead = new ImageIcon("../image/ICONES-CRUD-01.jpg");
		iUpdate = new ImageIcon("../image/ICONES-CRUD-03.jpg");

		// instancia botoes
		bPesquisar = new JButton();
		bLimpar = new JButton();
		bCadastrar = new JButton("", iCreate);
		bAlterar = new JButton("", iUpdate);
		bColsultar = new JButton("", iRead);

		// adiciona acao aos botoes
		bPesquisar.addActionListener(this);
		bLimpar.addActionListener(this);
		bCadastrar.addActionListener(this);
		bAlterar.addActionListener(this);
		bColsultar.addActionListener(this);

		// instancia label
		lConsultarEmpresa = new JLabel();
		lCnpj = new JLabel();
		lRazaoSocial = new JLabel();

		// edicao de titulo
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lConsultarEmpresa.setFont(fonteTitulo);
		lConsultarEmpresa.setAlignmentX(CENTER_ALIGNMENT);
		lConsultarEmpresa.setHorizontalAlignment(SwingConstants.CENTER);

		// instancia campo texto
		tfRazaoSocial = new JTextField(15);

		// instancia mascara
		try {
			mascaraCnpj = new MaskFormatter("###.###.###/####-##");
			mascaraCnpj.setPlaceholderCharacter('_');
		} catch (Exception e) {
			System.err.println("Erro na formata��o: " + e.getMessage());
			System.exit(-1);
		}

		ftfCnpj = new JFormattedTextField(mascaraCnpj);

		// instancia menu
		mArquivo = new JMenu();
		mEditar = new JMenu();

		// instancia item menu
		miSobre = new JMenuItem();
		miSair = new JMenuItem();
		miConsultar = new JMenuItem();
		miAlterar = new JMenuItem();
		miNovoCadastro = new JMenuItem();

		// listener item de menu
		miNovoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TelaCadastrarEmpresa tCE = new TelaCadastrarEmpresa(null);
			}
		});

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
		mEditar.add(miAlterar);
		mEditar.add(miNovoCadastro);

		// instancia modelo
		modelo = new TableModelEmpresa();
		String[] colunas = { bn.getString("telaConsulta.columnname.cnpj"),
				bn.getString("telaConsulta.columnname.razaosocial"),
				bn.getString("telaConsulta.columnname.horariofuncionamento"),
				bn.getString("telaConsulta.columnname.temperaturamaxima") };
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
		pForm.add(lCnpj, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.weightx = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(ftfCnpj, gBC);

		gBC.gridx = 2;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(lRazaoSocial, gBC);

		gBC.gridx = 3;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.weightx = 4;
		pForm.add(tfRazaoSocial, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(15, 5, 5, 5);
		pForm.add(bPesquisar, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		gBC.insets = new Insets(15, 5, 5, 5);
		pForm.add(bLimpar, gBC);

		gBC.gridx = 2;
		gBC.gridy = 3;
		gBC.insets = new Insets(35, 5, 5, 5);
		pForm.add(bAlterar, gBC);

		gBC.gridx = 3;
		gBC.gridy = 3;
		gBC.insets = new Insets(35, 5, 5, 5);
		pForm.add(bColsultar, gBC);

		gBC.gridx = 0;
		gBC.gridy = 3;
		gBC.insets = new Insets(35, 5, 5, 5);
		pForm.add(bCadastrar, gBC);

		// adiciona elementos ao painel
		pHeader.add(lConsultarEmpresa);
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
		if (e.getSource() == bCadastrar) {
			TelaCadastrarEmpresa tCE = new TelaCadastrarEmpresa(this);
		}
	}

	public void setComponentText() {
		bPesquisar.setText(bn.getString("telaConsultarEmpresa.botao.pesquisar"));
		bLimpar.setText(bn.getString("telaConsultarEmpresa.botao.limpar"));
		bCadastrar.setText(bn.getString("telaConsultarEmpresa.botao.cadastrar"));
		bAlterar.setText(bn.getString("telaConsultarEmpresa.botao.alterar"));
		bColsultar.setText(bn.getString("telaConsultarEmpresa.botao.consultar"));

		lConsultarEmpresa.setText(bn.getString("telaConsultarEmpresa.label.consultarempresa"));
		lCnpj.setText(bn.getString("telaConsultarEmpresa.label.cnpj"));
		lRazaoSocial.setText(bn.getString("telaConsultarEmpresa.label.razaosocial"));

		mArquivo.setText(bn.getString("telaPrincipal.menu.arquivo"));
		mEditar.setText(bn.getString("telaConsulta.menu.editar"));

		miSobre.setText(bn.getString("telaPrincipal.menuitem.sobre"));
		miSair.setText(bn.getString("telaPrincipal.menuitem.sair"));
		miConsultar.setText(bn.getString("telaConsulta.menuitem.consultar"));
		miAlterar.setText(bn.getString("telaConsulta.menuitem.alterar"));
		miNovoCadastro.setText(bn.getString("telaConsulta.menuitem.novocadastro"));

		pForm.setBorder(
				javax.swing.BorderFactory.createTitledBorder(bn.getString("telaConsultarEmpresa.botao.pesquisar")));

		setTitle(bn.getString("telaConsultarEmpresa.title"));

	}
}
