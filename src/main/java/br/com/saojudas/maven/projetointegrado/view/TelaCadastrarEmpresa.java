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
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.saojudas.maven.projetointegrado.model.Empresa;
import br.com.saojudas.maven.projetointegrado.model.Status;

public class TelaCadastrarEmpresa extends JDialog implements ActionListener {
	// componentes formul�rio
	private JLabel lTitulo, lCnpj, lRazaoSocial, lConjuntos, lHorario, lTemperaturaMax;
	private JTextField tfRazaoSocial;
	private JComboBox<String> cbConjuntos;
	private JButton bSalvar, bLimpar, bBuscaEmpresa;
	private JFormattedTextField ftfCnpj, ftfHorario, ftfTemperaturaMaxima;

	// atributo mascara
	MaskFormatter mascaraCnpj = null, mascaraHorario = null, mascaraTemperaturaMaxima = null;

	// atributos paineis
	private JPanel pNorte, pCentro, pSul, pBuscaEmpresa;

	// Container
	Container container;

	// atributo para manipulacao de idiomas
	private ResourceBundle bn = TelaPrincipal.bn;

	// atributo usuario
	private static Empresa empresa;

	// atributo status da tela
	static EstadoTela estadoTela;

	public TelaCadastrarEmpresa(JFrame fr, Empresa empresa) {
		// invoca o metodo construtor da superclasse
		super(fr, true);

		// determina o idioma padrao para portugues
		// bn = ResourceBundle.getBundle("idioma", new Locale("pt", "BR"));

		AplicaLookAndFeel.lookAndFeel();

		// Configura o Container
		container = getContentPane();
		container.setLayout(new BorderLayout(20, 20));

		// Instancia e configura o componente Titulo
		lTitulo = new JLabel();
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lTitulo.setFont(fonteTitulo);
		lTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		// Instancia os componentes do Centro
		lCnpj = new JLabel();
		lRazaoSocial = new JLabel();
		lConjuntos = new JLabel();
		lHorario = new JLabel();
		lTemperaturaMax = new JLabel();

		tfRazaoSocial = new JTextField(15);

		// instancia mascara
		try {
			mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			mascaraCnpj.setPlaceholderCharacter('_');

			mascaraHorario = new MaskFormatter("##:## a ##:##");
			mascaraHorario.setPlaceholderCharacter('_');

			mascaraTemperaturaMaxima = new MaskFormatter("## C�");
			mascaraTemperaturaMaxima.setPlaceholderCharacter('_');
		} catch (Exception e) {
			System.err.println("Erro na formata��o: " + e.getMessage());
			System.exit(-1);
		}

		ftfCnpj = new JFormattedTextField(mascaraCnpj);
		ftfHorario = new JFormattedTextField(mascaraHorario);
		ftfTemperaturaMaxima = new JFormattedTextField(mascaraTemperaturaMaxima);

		cbConjuntos = new JComboBox<String>();
		
		cbConjuntos.addItem("Conjunto 1");

		// Instancia os componentes do Sul
		bSalvar = new JButton();
		bLimpar = new JButton();
		// bBuscaEmpresa = new JButton(" ... ");

		// bBuscaEmpresa.addActionListener(this);
		bSalvar.addActionListener(this);
		bLimpar.addActionListener(this);

		// instancia paineis
		pNorte = new JPanel(new GridLayout(1, 1, 5, 5));
		pCentro = new JPanel(new GridBagLayout());
		pSul = new JPanel(new GridBagLayout());
		pBuscaEmpresa = new JPanel(new GridBagLayout());

		// instancia borda para os paineis
		pCentro.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
		pNorte.setBorder(new EmptyBorder(10, 10, 10, 10));
		pSul.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Adiciona Componentes aos Panels

		pNorte.add(lTitulo);

		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.weightx = 1;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(lCnpj, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(ftfCnpj, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(lRazaoSocial, gBC);

		gBC.gridx = 1;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(tfRazaoSocial, gBC);

		gBC.gridx = 0;
		gBC.gridy = 2;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(lConjuntos, gBC);

		gBC.gridx = 1;
		gBC.gridy = 2;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(cbConjuntos, gBC);

		gBC.gridx = 0;
		gBC.gridy = 3;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(lHorario, gBC);

		gBC.gridx = 1;
		gBC.gridy = 3;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(ftfHorario, gBC);

		gBC.gridx = 0;
		gBC.gridy = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(lTemperaturaMax, gBC);

		gBC.gridx = 1;
		gBC.gridy = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pCentro.add(ftfTemperaturaMaxima, gBC);
		gBC.weightx = 0;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(bSalvar, gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 5, 5);
		pSul.add(bLimpar, gBC);

		// gBC.gridx = 0;
		// gBC.gridy = 0;
		// gBC.insets = new Insets(5, 5, 5, 5);
		// pBuscaEmpresa.add(ftfCnpj, gBC);
		//
		// gBC.gridx = 1;
		// gBC.gridy = 0;
		// gBC.insets = new Insets(5, 5, 5, 5);
		// pBuscaEmpresa.add(bBuscaEmpresa, gBC);

		// adiciona os paineis ao container

		container.add(BorderLayout.NORTH, pNorte);
		container.add(BorderLayout.CENTER, pCentro);
		container.add(BorderLayout.SOUTH, pSul);

		estadoTela = TelaConsultarEmpresa.alteraEstadoTela;

		alteraTipoDeTela(empresa);

		// metodo que atualiza o texto de todos os componentes
		setComponentText();

		// congifura o painel
		setSize(650, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		// setExtendedState(MAXIMIZED_BOTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bSalvar) {
			if (estadoTela == EstadoTela.CADASTRAR) {
				try {
					empresa = new Empresa(
							ftfCnpj.getText().replace("_", "").replace(".", "").replace("-", "").replace("/", ""),
							tfRazaoSocial.getText(), ftfHorario.getText().replace("_", ""),
							Integer.parseInt(ftfTemperaturaMaxima.getText().replaceAll("[^0123456789]", "")),
							Status.ATIVO);
					dispose();
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			} else if (estadoTela == EstadoTela.ALTERAR) {
				try {
					empresa = new Empresa(
							ftfCnpj.getText().replace("_", "").replace(".", "").replace("-", "").replace("/", ""),
							tfRazaoSocial.getText(), ftfHorario.getText().replace("_", ""),
							Integer.parseInt(ftfTemperaturaMaxima.getText().replaceAll("[^0123456789]", "")),
							Status.ATIVO);

					dispose();
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (e.getSource() == bLimpar) {
			ftfCnpj.setText("");
			tfRazaoSocial.setText("");
			ftfHorario.setText("");
			ftfTemperaturaMaxima.setText("");
		}
	}

	public void setComponentText() {
		lTitulo.setText(bn.getString("telaCadastrarEmpresa.label.titulo"));
		lCnpj.setText(bn.getString("telaCadastrarEmpresa.label.cnpj"));
		lRazaoSocial.setText(bn.getString("telaCadastrarEmpresa.label.razaoSocial"));
		lConjuntos.setText(bn.getString("telaCadastrarEmpresa.label.conjuntos"));
		lHorario.setText(bn.getString("telaCadastrarEmpresa.label.horario"));
		lTemperaturaMax.setText(bn.getString("telaCadastrarEmpresa.label.temperaturaMax"));
		bSalvar.setText(bn.getString("telaCadastrarEmpresa.button.salvar"));
		bLimpar.setText(bn.getString("telaCadastrarEmpresa.button.limpar"));
		setTitle(bn.getString("telaCadastrarEmpresa.label.titulo"));
	}

	static Empresa cadastrarEmpresa(JFrame fr) {
		TelaCadastrarEmpresa cadastrarUsuario = new TelaCadastrarEmpresa(fr, null);
		return empresa;
	}

	static Empresa alteraEmpresa(JFrame fr, Empresa empresaAlterada) {
		TelaCadastrarEmpresa cadastrarEmpresa = new TelaCadastrarEmpresa(fr, empresaAlterada);
		return empresa;
	}

	public void alteraTipoDeTela(Empresa empresa) {
		if (estadoTela == EstadoTela.CONSULTAR) {
			ftfCnpj.setText(empresa.getCnpj());
			tfRazaoSocial.setText(empresa.getRazaoSocial());
			ftfHorario.setText(empresa.getHorarioDeFuncionamento());
			ftfTemperaturaMaxima.setText(empresa.getTemperaturaMaximaArCondicionado() + "");

			// desativa a edição
			ftfCnpj.setEnabled(false);
			tfRazaoSocial.setEnabled(false);
			ftfHorario.setEnabled(false);
			ftfTemperaturaMaxima.setEnabled(false);

		} else if (estadoTela == EstadoTela.CADASTRAR) {

		} else {
			ftfCnpj.setText(empresa.getCnpj());
			tfRazaoSocial.setText(empresa.getRazaoSocial());
			ftfHorario.setText(empresa.getHorarioDeFuncionamento());
			ftfTemperaturaMaxima.setText(empresa.getTemperaturaMaximaArCondicionado() + "");

			// desativa a edição
			bSalvar.setEnabled(true);
			bLimpar.setEnabled(true);
			ftfCnpj.setEnabled(true);
			ftfHorario.setEnabled(true);
			tfRazaoSocial.setEnabled(true);
			ftfTemperaturaMaxima.setEnabled(true);
		}
	}

}
