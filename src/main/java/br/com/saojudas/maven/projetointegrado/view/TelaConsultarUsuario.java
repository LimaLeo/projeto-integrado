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
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

import br.com.saojudas.maven.projetointegrado.components.TableModelUsuario;
import br.com.saojudas.maven.projetointegrado.control.UsuarioCtrl;
import br.com.saojudas.maven.projetointegrado.model.Usuario;

public class TelaConsultarUsuario extends JFrame implements ActionListener
{
	// atributos para formulario
	private JButton bPesquisar, bLimpar, bCadastrar, bAlterar, bConsultar;
	private JLabel lConsultarUsuario;
	private JTextField tfNome;
	private JFormattedTextField ftfCpf;
	private ButtonGroup bgFiltroBusca;
	private JRadioButton rbCpf, rbNome;

	// atributo classe controller
	private UsuarioCtrl usuarioCtrl;

	// atributo mascara
	MaskFormatter mascaraCpf = null;

	// atributos para o menu
	private JMenu mArquivo, mEditar;
	private JMenuItem miSobre, miSair, miAlterar, miNovoCadastro, miConsultar;
	private JMenuBar mbMenu;

	// atributo para manipulacao de idiomas
	private ResourceBundle bn = TelaPrincipal.bn;

	// atributos paineis
	private JPanel pHeader, pForm, pMain, pContent, pFooter;

	// atributos para tabela
	private JTable tTabela;
	private JScrollPane spEmpresa;
	private TableModelUsuario modelo;

	private ArrayList<Usuario> usuarios;

	static EstadoTela alteraEstadoTela;

	public TelaConsultarUsuario()
	{
		// determina o idioma padrao para portugues
		// bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));

		Container container = getContentPane();// container
		container.setLayout(new BorderLayout());// instancia e atribui ao
		// layout border

		AplicaLookAndFeel.lookAndFeel();

		// instancia radioButtons
		rbCpf = new JRadioButton();
		rbNome = new JRadioButton();
		bgFiltroBusca = new ButtonGroup();
		bgFiltroBusca.add(rbCpf);
		bgFiltroBusca.add(rbNome);
		rbCpf.setSelected(true);

		rbCpf.addActionListener(this);
		rbNome.addActionListener(this);

		// instancia botoes
		bPesquisar = new JButton();
		bLimpar = new JButton();
		bCadastrar = new JButton();
		bAlterar = new JButton();
		bConsultar = new JButton();

		// adiciona acao aos botoes
		bPesquisar.addActionListener(this);
		bLimpar.addActionListener(this);
		bCadastrar.addActionListener(this);
		bAlterar.addActionListener(this);
		bConsultar.addActionListener(this);

		// instancia label
		lConsultarUsuario = new JLabel();

		// edicao de titulo
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lConsultarUsuario.setFont(fonteTitulo);
		lConsultarUsuario.setAlignmentX(CENTER_ALIGNMENT);
		lConsultarUsuario.setHorizontalAlignment(SwingConstants.CENTER);

		// instancia campo texto
		tfNome = new JTextField(15);
		tfNome.setEnabled(false);

		// instancia mascara
		try
		{
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
		}
		catch (Exception e)
		{
			System.err.println("Erro na formata��o: " + e.getMessage());
			System.exit(-1);
		}

		ftfCpf = new JFormattedTextField(mascaraCpf);

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
		miNovoCadastro.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				TelaCadastrarUsuario tCU = new TelaCadastrarUsuario(null, null);
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
		modelo = new TableModelUsuario();
		String[] colunas =
		{ bn.getString("telaConsulta.columnname.tipousuario"), bn.getString("telaConsulta.columnname.nome"),
				bn.getString("telaConsulta.columnname.cpf"), };
		modelo.setColunas(colunas);

		// instancia e atribui os usuarios cadastrados no banco
		usuarioCtrl = new UsuarioCtrl();
		usuarios = (ArrayList) usuarioCtrl.consultarTodosUsuario();

		// carrega usuarios atuais
		modelo.setAlUsuario(usuarios);

		// instancia a table e atribui ao modelo criado
		tTabela = new JTable(modelo);
		tTabela.setFillsViewportHeight(true);

		// instancia RowSorter
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tTabela.setRowSorter(sorter);

		// instancia jscrollpane
		spEmpresa = new JScrollPane(tTabela);
		spEmpresa.setPreferredSize(new Dimension(650, 450));

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
		pForm.add(rbCpf, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.weightx = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(ftfCpf, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(rbNome, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.weightx = 4;
		pForm.add(tfNome, gBC);

		gBC.gridx = 0;
		gBC.gridy = 2;
		gBC.insets = new Insets(15, 5, 5, 5);
		pForm.add(bPesquisar, gBC);

		gBC.gridx = 1;
		gBC.gridy = 2;
		gBC.insets = new Insets(15, 5, 5, 5);
		pForm.add(bLimpar, gBC);

		gBC.gridx = 2;
		gBC.gridy = 3;
		gBC.insets = new Insets(35, 5, 5, 5);
		pForm.add(bAlterar, gBC);

		gBC.gridx = 3;
		gBC.gridy = 3;
		gBC.insets = new Insets(35, 5, 5, 5);
		pForm.add(bConsultar, gBC);

		gBC.gridx = 0;
		gBC.gridy = 3;
		gBC.insets = new Insets(35, 5, 5, 5);
		pForm.add(bCadastrar, gBC);

		// adiciona elementos ao painel
		pHeader.add(lConsultarUsuario);
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
		setSize(650, 550);
		setLocation(350, 80);
		setVisible(true);
		// setExtendedState(MAXIMIZED_BOTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bPesquisar)
		{

			// Verifica se a busca é por cpf ou nome

			if (rbCpf.isSelected())
			{
				String cpf = ftfCpf.getText().replace("_", "").replace(".", "").replace("-", "");
				usuarios = (ArrayList) usuarioCtrl.consultarTodosUsuarioCpf(cpf);

				// carrega empresas atuais
				modelo.setAlUsuario(usuarios);
				this.repaint();
			}
			else
			{
				String nome = tfNome.getText();
				usuarios = (ArrayList) usuarioCtrl.consultarTodosUsuarioNome(nome);

				// carrega empresas atuais
				modelo.setAlUsuario(usuarios);
				this.repaint();
			}
		}
		if (e.getSource() == bLimpar)
		{
			ftfCpf.setText("");
			tfNome.setText("");
		}
		if (e.getSource() == bCadastrar)
		{
			try
			{
				alteraEstadoTela = EstadoTela.CADASTRAR;
				Usuario usuario = TelaCadastrarUsuario.cadastrarUsuario(this);

				usuarioCtrl.incluirUsuario(usuario);

				// adiciona usuario na lista de usuarios
				modelo.addUsuario(usuario);

				// carrega usuarios
				modelo.setAlUsuario(usuarios);

			}
			catch (Exception e2)
			{
				JOptionPane.showMessageDialog(null, "Usuario nao foi cadastrado!", "Erro ao cadastrar",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == bConsultar)
		{
			try
			{
				// chama a linha selecionada
				int linhaSelecionada = this.tTabela.getSelectedRow();
				Usuario usuario = new Usuario();
				usuario = modelo.carregaUsuario(linhaSelecionada);

				alteraEstadoTela = EstadoTela.CONSULTAR;
				TelaCadastrarUsuario cadastrarUsuario = new TelaCadastrarUsuario(null, usuario);

				// usuario = usuarioCtrl.consultaUsuario(usuario.getCpf());
				// System.out.println(usuario.getId());
			}
			catch (Exception e2)
			{
				JOptionPane.showMessageDialog(null, "Selecione um dos itens listados (clique sobre ele)!",
						"Erro ao selecionar", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == bAlterar)
		{
			try
			{
				// chama a linha selecionada
				int linhaSelecionada = this.tTabela.getSelectedRow();
				Usuario usuario = new Usuario();
				Usuario pegaUsuario = modelo.carregaUsuario(linhaSelecionada);
				
				alteraEstadoTela = EstadoTela.ALTERAR;
				
				if(TelaPrincipal.nivelAcesso == 2)
				{
					String tipo = ""+pegaUsuario.getTipoUsuario();
					if(tipo.equals("SINDICO"))
					{
						JOptionPane.showMessageDialog(null, "Você não tem permissao para alterar um Sindico!");
						return;
					}
				}
				// carrega usuario do banco
				usuario = TelaCadastrarUsuario.alteraUsuario(null, pegaUsuario);
				
				
				
				// altera conforme campo alterado
				if (TelaCadastrarUsuario.confirma == true)
				{
					pegaUsuario.setNome(usuario.getNome());
					pegaUsuario.setCpf(usuario.getCpf());
					pegaUsuario.setLogin(usuario.getLogin());
					pegaUsuario.setSenha(usuario.getSenha());
					pegaUsuario.setHorarioDeAcesso(usuario.getHorarioDeAcesso());
					pegaUsuario.setTipoUsuario(usuario.getTipoUsuario());
					pegaUsuario.setAcessoLivre(usuario.isAcessoLivre());
					pegaUsuario.setPermissaoAlterarTemperatura(usuario.isPermissaoAlterarTemperatura());
					pegaUsuario.setEmpresa(usuario.getEmpresa());

					// faz o merge dos dados alterados
					usuarioCtrl.alteraUsuario(pegaUsuario.getId(), pegaUsuario);
				}
				// atualiza lista
				modelo.setAlUsuario(usuarios);

			}
			catch (Exception e2)
			{
				JOptionPane.showMessageDialog(null, "Usuario nao foi alterado!", "Erro ao alterar",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == rbCpf)
		{
			ftfCpf.setEnabled(true);
			tfNome.setEnabled(false);
		}

		if (e.getSource() == rbNome)
		{
			ftfCpf.setEnabled(false);
			tfNome.setEnabled(true);
		}
	}

	public void setComponentText()
	{
		bPesquisar.setText(bn.getString("telaConsultarEmpresa.botao.pesquisar"));
		bLimpar.setText(bn.getString("telaConsultarEmpresa.botao.limpar"));
		bCadastrar.setText(bn.getString("telaConsultarEmpresa.botao.cadastrar"));
		bAlterar.setText(bn.getString("telaConsultarEmpresa.botao.alterar"));
		bConsultar.setText(bn.getString("telaConsultarEmpresa.botao.consultar"));

		lConsultarUsuario.setText(bn.getString("telaConsultarUsuario.label.consultarusuario"));
		rbCpf.setText(bn.getString("telaConsultarUsuario.label.cpf"));
		rbNome.setText(bn.getString("telaConsultarUsuario.label.nome"));

		mArquivo.setText(bn.getString("telaPrincipal.menu.arquivo"));
		mEditar.setText(bn.getString("telaConsulta.menu.editar"));

		miSobre.setText(bn.getString("telaPrincipal.menuitem.sobre"));
		miSair.setText(bn.getString("telaPrincipal.menuitem.sair"));
		miConsultar.setText(bn.getString("telaConsulta.menuitem.consultar"));
		miAlterar.setText(bn.getString("telaConsulta.menuitem.alterar"));
		miNovoCadastro.setText(bn.getString("telaConsulta.menuitem.novocadastro"));

		pForm.setBorder(
				javax.swing.BorderFactory.createTitledBorder(bn.getString("telaConsultarEmpresa.botao.pesquisar")));

		setTitle(bn.getString("telaConsultarUsuario.title"));
	}
}
