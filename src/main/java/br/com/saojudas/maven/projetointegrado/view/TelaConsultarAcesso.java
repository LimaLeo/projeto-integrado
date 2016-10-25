package br.com.saojudas.maven.projetointegrado.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
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

import br.com.saojudas.maven.projetointegrado.components.TableModelAcesso;
import br.com.saojudas.maven.projetointegrado.control.AcessoCtrl;
import br.com.saojudas.maven.projetointegrado.model.Acesso;

public class TelaConsultarAcesso extends JDialog implements ActionListener
{
	// atributos para formulario
	private JButton bPesquisar, bLimpar;
	private JLabel lConsultarAcesso, lData, lEmpresa, lTipoDeUsuario;
	private JTextField tfRazaoSocial;
	private JCheckBox ckbSindico, ckbAtendente;
	private JFormattedTextField ftfDataInicio, ftfDataFim;
	private ButtonGroup bgFiltroBusca;
	private JRadioButton rbData, rbEmpresa;

	// atributo classe controller
	private AcessoCtrl acessoCtrl;

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

	private ArrayList<Acesso> acessos;

	public TelaConsultarAcesso(JFrame fr)
	{
		// invoca o metodo construtor da superclasse
		super(fr, true);
		// determina o idioma selecionado
		// bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));

		Container container = getContentPane();// container
		container.setLayout(new BorderLayout());// instancia e atribui ao
		// layout border

		AplicaLookAndFeel.lookAndFeel();

		// instancia radioButtons
		rbData = new JRadioButton();
		rbEmpresa = new JRadioButton();
		bgFiltroBusca = new ButtonGroup();
		bgFiltroBusca.add(rbData);
		bgFiltroBusca.add(rbEmpresa);
		rbData.setSelected(true);

		rbData.addActionListener(this);
		rbEmpresa.addActionListener(this);

		// instancia �cones
		iCreate = new ImageIcon("../image/ICONES-CRUD-02.jpg");
		iRead = new ImageIcon("../image/ICONES-CRUD-01.jpg");
		iUpdate = new ImageIcon("../image/ICONES-CRUD-03.jpg");

		// instancia botoes
		bPesquisar = new JButton();
		bLimpar = new JButton();

		// adiciona acao aos botoes
		bPesquisar.addActionListener(this);
		bLimpar.addActionListener(this);

		// instancia label
		lConsultarAcesso = new JLabel();
		lData = new JLabel();
		lEmpresa = new JLabel();
		lTipoDeUsuario = new JLabel();

		// radios buttons para tipos de usuario
		ckbAtendente = new JCheckBox();
		ckbSindico = new JCheckBox();
		ckbAtendente.setSelected(true);
		ckbSindico.setSelected(true);
		

		// edicao de titulo
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lConsultarAcesso.setFont(fonteTitulo);
		lConsultarAcesso.setAlignmentX(CENTER_ALIGNMENT);
		lConsultarAcesso.setHorizontalAlignment(SwingConstants.CENTER);

		// instancia campo texto
		tfRazaoSocial = new JTextField(15);
		tfRazaoSocial.setEnabled(false);

		// instancia mascara
		try
		{
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
		}
		catch (Exception e)
		{
			System.err.println("Erro na formata��o: " + e.getMessage());
			System.exit(-1);
		}

		ftfDataInicio = new JFormattedTextField(mascaraData);
		ftfDataFim = new JFormattedTextField(mascaraData);

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
		String[] colunas =
		{ bn.getString("telaConsulta.columnname.tipousuario"), bn.getString("telaConsulta.columnname.nome"), bn.getString("telaConsulta.columnname.cpf"),
				bn.getString("telaConsulta.columnname.empresa"), bn.getString("telaConsulta.columnname.entrada"),
				bn.getString("telaConsulta.columnname.saida")};
		modelo.setColunas(colunas);

		// instancia e atribui os acessos cadastrados no banco
		acessoCtrl = new AcessoCtrl();
		acessos = (ArrayList) acessoCtrl.consultarTodosAcessos();

		// carrega usuarios atuais
		modelo.setAlAcesso(acessos);

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
		pForm.add(rbData, gBC);

		JPanel pData = new JPanel();
		pData.setLayout(new FlowLayout(FlowLayout.LEFT));

		pData.add(ftfDataInicio);
		pData.add(new JLabel(" : "));
		pData.add(ftfDataFim);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.weightx = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(pData, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pForm.add(rbEmpresa, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.weightx = 4;
		pForm.add(tfRazaoSocial, gBC);

		gBC.gridx = 0;
		gBC.gridy = 2;
		pForm.add(lTipoDeUsuario, gBC);

		gBC.gridx = 1;
		gBC.gridy = 2;
		pForm.add(ckbSindico, gBC);

		gBC.gridx = 1;
		gBC.gridy = 3;
		pForm.add(ckbAtendente, gBC);

		gBC.gridx = 0;
		gBC.gridy = 4;
		gBC.insets = new Insets(10, 5, 5, 5);
		pForm.add(bPesquisar, gBC);

		gBC.gridx = 1;
		gBC.gridy = 4;
		gBC.insets = new Insets(10, 5, 5, 5);
		pForm.add(bLimpar, gBC);

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
		setSize(650, 550);
		setLocation(350, 80);
		setVisible(true);
		// setExtendedState(MAXIMIZED_BOTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bPesquisar)
		{
			// Verifica qual foi a busca
			if (rbData.isSelected()) // Busca por datas
			{
				String dataIni = ftfDataInicio.getText();
				String dataFim = ftfDataFim.getText();

				// validar as datas
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dataI = null;
				Date dataF = null;
				try
				{
					dataI = (Date) dateFormat.parse(dataIni);
					dataF = (Date) dateFormat.parse(dataFim);

					long dias = dataF.getTime() - dataI.getTime();
					dias = dias / (24 * 60 * 60 * 1000);

					if (dataI.after(dataF))
					{
						JOptionPane.showMessageDialog(null, "Data Inicial não pode ser maior que a final!");
					}
					else if (dias > 365)
					{
						JOptionPane.showMessageDialog(null, "O Período não pode ser maior que 1 ano");
					}
					else if (Integer.parseInt(dataIni.substring(0, 2)) > 32
							|| Integer.parseInt(dataIni.substring(3, 5)) > 12
							|| Integer.parseInt(dataFim.substring(0, 2)) > 30
							|| Integer.parseInt(dataFim.substring(3, 5)) > 12)
					{
						JOptionPane.showMessageDialog(null, "Data inválida");
					}
					else
					{
						dataIni = dataIni.replace("/", "-");
						dataFim = dataFim.replace("/", "-");

						// Converte o padrao de data br para o en
						dataIni = dataIni.substring(6, 10) + "-" + dataIni.substring(3, 5) + "-"
								+ dataIni.substring(0, 2);
						dataFim = dataFim.substring(6, 10) + "-" + dataFim.substring(3, 5) + "-"
								+ dataFim.substring(0, 2);

						acessos = (ArrayList) acessoCtrl.consultarTodosAcessosData(dataIni, dataFim);

						// carrega empresas atuais

						// Verifica se quer Atendente, Sinidico, Nenhum ou os
						// Dois
						if (ckbAtendente.isSelected() && ckbSindico.isSelected()) // os
																					// dois
						{
							modelo.setAlAcesso(acessos);
							this.repaint();
						}
						else if (ckbAtendente.isSelected() && !ckbSindico.isSelected()) // só
																						// atendente
						{
							modelo.setAlAcesso(modelo.getAcessosAtendente(acessos));
							this.repaint();
						}
						else if (!ckbAtendente.isSelected() && ckbSindico.isSelected()) // só
																						// sindico
						{
							modelo.setAlAcesso(modelo.getAcessosSindico(acessos));
							this.repaint();
						}
						else// nenhum(só funcionario)
						{
							modelo.setAlAcesso(modelo.getAcessosFuncionario(acessos));
							this.repaint();
						}

					}
				}
				catch (ParseException e1)
				{
					JOptionPane.showMessageDialog(null, "Data Inválida!");
				}

			}
			else //radio button Empresa ta selecionado
			{
				acessos = (ArrayList) acessoCtrl.consultarTodosAcessos();				
				String nomeEmpresa = tfRazaoSocial.getText().toLowerCase().trim();
				acessos = modelo.getAcessosEmpresa(acessos, nomeEmpresa);
				
				// Verifica se quer Atendente, Sinidico, Nenhum ou os
				// Dois
				if (ckbAtendente.isSelected() && ckbSindico.isSelected()) // os
																			// dois
				{					
					modelo.setAlAcesso(acessos);
					this.repaint();
				}
				else if (ckbAtendente.isSelected() && !ckbSindico.isSelected()) // só
																				// atendente
				{
					modelo.setAlAcesso(modelo.getAcessosAtendente(acessos));
					this.repaint();
				}
				else if (!ckbAtendente.isSelected() && ckbSindico.isSelected()) // só
																				// sindico
				{
					modelo.setAlAcesso(modelo.getAcessosSindico(acessos));
					this.repaint();
				}
				else// nenhum(só funcionario)
				{
					modelo.setAlAcesso(modelo.getAcessosFuncionario(acessos));
					this.repaint();
				}
				
				
				this.repaint();
			}
		}
		if (e.getSource() == bLimpar)
		{
			ftfDataInicio.setText("");
			ftfDataFim.setText("");
			tfRazaoSocial.setText("");
		
		}
		if (e.getSource() == rbData)
		{
			ftfDataInicio.setEnabled(true);
			ftfDataFim.setEnabled(true);
			tfRazaoSocial.setEnabled(false);
		}
		if (e.getSource() == rbEmpresa)
		{
			ftfDataInicio.setEnabled(false);
			ftfDataFim.setEnabled(false);
			tfRazaoSocial.setEnabled(true);
		}
	}

	public void setComponentText()
	{
		bPesquisar.setText(bn.getString("telaConsultarAcesso.botao.pesquisar"));
		bLimpar.setText(bn.getString("telaConsultarAcesso.botao.limpar"));

		lConsultarAcesso.setText(bn.getString("telaConsultarAcesso.label.consultaracesso"));
		rbData.setText(bn.getString("telaConsultarAcesso.label.data"));
		rbEmpresa.setText(bn.getString("telaConsultarAcesso.label.empresa"));
		lTipoDeUsuario.setText(bn.getString("telaConsultarAcesso.label.tipodeusuario"));
		ckbSindico.setText(bn.getString("telaCadastrarUsuario.radioButton.sindico"));
		ckbAtendente.setText(bn.getString("telaCadastrarUsuario.radioButton.atendente"));

		mArquivo.setText(bn.getString("telaPrincipal.menu.arquivo"));
		mEditar.setText(bn.getString("telaConsulta.menu.editar"));

		miSobre.setText(bn.getString("telaPrincipal.menuitem.sobre"));
		miSair.setText(bn.getString("telaPrincipal.menuitem.sair"));
		miConsultar.setText(bn.getString("telaConsulta.menuitem.consultar"));

		setTitle(bn.getString("telaConsultarAcesso.title"));
	}
}
