package br.com.estetica.view;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.com.estetica.model.InfoProfissionais;
import br.com.estetica.profissionaisDAO.ComandosBancoProfissionais;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class ProfissionaisTela {

	private JFrame frame;
	private JTextField textNomeApelido;
	private JTextField textNomeCompleto;
	private JTextField textCel;
	private JTextField textAniver;
	private JTextField textCep;
	private JTextField textRua;
	private JTextField textNum;
	private JTextField textComp;
	private JTextField textBairro;
	private JTextField textCidade;
	private JTextField textEstado;
	private JTable tabela;
	private JTextField textLocalizar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfissionaisTela window = new ProfissionaisTela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProfissionaisTela() {
		initialize();
		tableLoad();
	}

	public void tableLoad() {
		try {
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/profissionais",
					"root", "");
			PreparedStatement pst = (PreparedStatement) con
					.prepareStatement("SELECT nome_completo FROM cadastro_profissionais ORDER BY nome_completo ASC");
			ResultSet rs = pst.executeQuery();
			tabela.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// importando as config do banco
		ComandosBancoProfissionais profissionaisDB = new ComandosBancoProfissionais();
		// info dos clientes
		InfoProfissionais exec = new InfoProfissionais();

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 641);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblProfissionais = new JLabel("Profissionais");
		lblProfissionais.setFont(new Font("Arial", Font.PLAIN, 20));
		lblProfissionais.setBounds(361, 11, 115, 31);
		frame.getContentPane().add(lblProfissionais);

		JPanel panelDados = new JPanel();
		panelDados.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDados.setBounds(10, 48, 396, 385);
		frame.getContentPane().add(panelDados);
		panelDados.setLayout(null);

		JLabel LblNomeCompleto = new JLabel("Nome Completo:");
		LblNomeCompleto.setBounds(10, 53, 116, 18);
		LblNomeCompleto.setFont(new Font("Arial", Font.PLAIN, 15));
		panelDados.add(LblNomeCompleto);

		JLabel LblNomeApelido_1 = new JLabel("Nome/Apelido:");
		LblNomeApelido_1.setFont(new Font("Arial", Font.PLAIN, 15));
		LblNomeApelido_1.setBounds(10, 23, 106, 18);
		panelDados.add(LblNomeApelido_1);

		JLabel LblCelular = new JLabel("Celular:");
		LblCelular.setFont(new Font("Arial", Font.PLAIN, 15));
		LblCelular.setBounds(10, 82, 106, 18);
		panelDados.add(LblCelular);

		JLabel LblAniversario = new JLabel("Aniversário:");
		LblAniversario.setFont(new Font("Arial", Font.PLAIN, 15));
		LblAniversario.setBounds(10, 111, 106, 18);
		panelDados.add(LblAniversario);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Endereço", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 144, 377, 230);
		panelDados.add(panel_1);
		panel_1.setLayout(null);

		JLabel LblCEP = new JLabel("CEP:");
		LblCEP.setBounds(10, 22, 106, 18);
		panel_1.add(LblCEP);
		LblCEP.setFont(new Font("Arial", Font.PLAIN, 15));

		JLabel LblRua = new JLabel("Rua:");
		LblRua.setFont(new Font("Arial", Font.PLAIN, 15));
		LblRua.setBounds(10, 51, 106, 18);
		panel_1.add(LblRua);

		JLabel LblComplemento = new JLabel("Complemento:");
		LblComplemento.setFont(new Font("Arial", Font.PLAIN, 15));
		LblComplemento.setBounds(10, 109, 106, 18);
		panel_1.add(LblComplemento);

		JLabel LblNumero = new JLabel("Numero:");
		LblNumero.setFont(new Font("Arial", Font.PLAIN, 15));
		LblNumero.setBounds(10, 80, 106, 18);
		panel_1.add(LblNumero);

		JLabel LblBairro = new JLabel("Bairro:");
		LblBairro.setFont(new Font("Arial", Font.PLAIN, 15));
		LblBairro.setBounds(10, 138, 106, 18);
		panel_1.add(LblBairro);

		JLabel LbCidade = new JLabel("Cidade:");
		LbCidade.setFont(new Font("Arial", Font.PLAIN, 15));
		LbCidade.setBounds(10, 167, 106, 18);
		panel_1.add(LbCidade);

		JLabel LblEstado = new JLabel("Estado:");
		LblEstado.setFont(new Font("Arial", Font.PLAIN, 15));
		LblEstado.setBounds(10, 196, 106, 18);
		panel_1.add(LblEstado);

		textCep = new JTextField();
		textCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cep = textCep.getText().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

				if (!cep.isEmpty()) {
					JSONObject endereco = buscarCEP(cep);

					if (endereco != null) {
						textRua.setText(endereco.getString("logradouro"));
						textComp.setText(endereco.getString("complemento"));
						textBairro.setText(endereco.getString("bairro"));
						textCidade.setText(endereco.getString("localidade"));
						textEstado.setText(endereco.getString("uf"));
					}
				}
			}
		});

		textCep.setBounds(54, 22, 154, 20);
		panel_1.add(textCep);
		textCep.setColumns(10);

		textRua = new JTextField();
		textRua.setBounds(48, 51, 191, 20);
		panel_1.add(textRua);
		textRua.setColumns(10);

		textNum = new JTextField();
		textNum.setBounds(73, 80, 86, 20);
		panel_1.add(textNum);
		textNum.setColumns(10);

		textComp = new JTextField();
		textComp.setBounds(111, 109, 128, 20);
		panel_1.add(textComp);
		textComp.setColumns(10);

		textBairro = new JTextField();
		textBairro.setBounds(55, 138, 184, 20);
		panel_1.add(textBairro);
		textBairro.setColumns(10);

		textCidade = new JTextField();
		textCidade.setBounds(73, 167, 166, 20);
		panel_1.add(textCidade);
		textCidade.setColumns(10);

		textEstado = new JTextField();
		textEstado.setBounds(73, 196, 166, 20);
		panel_1.add(textEstado);
		textEstado.setColumns(10);

		textNomeApelido = new JTextField();
		textNomeApelido.setBounds(111, 23, 276, 20);
		panelDados.add(textNomeApelido);
		textNomeApelido.setColumns(10);

		textNomeCompleto = new JTextField();
		textNomeCompleto.setBounds(121, 53, 266, 18);
		panelDados.add(textNomeCompleto);
		textNomeCompleto.setColumns(10);

		textCel = new JTextField();
		textCel.setBounds(64, 82, 156, 20);
		panelDados.add(textCel);
		textCel.setColumns(10);

		textAniver = new JTextField();
		textAniver.setBounds(92, 111, 127, 20);
		panelDados.add(textAniver);
		textAniver.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(416, 53, 358, 380);
		frame.getContentPane().add(scrollPane);

		tabela = new JTable();
		scrollPane.setViewportView(tabela);

		// adicionar as registro
		JButton btnNovo = new JButton("Cadastrar");
		btnNovo.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeCompleto = textNomeCompleto.getText();
				String apelido = textNomeApelido.getText();
				String aniversario = textAniver.getText();
				String celular = textCel.getText();
				String cep = textCep.getText();
				String rua = textRua.getText();
				String comp = textComp.getText();
				int num = Integer.parseInt(textNum.getText());
				String bairro = textBairro.getText();
				String cidade = textCidade.getText();
				String estado = textEstado.getText();

				exec.setNomeCompleto(nomeCompleto);
				exec.setApelido(apelido);
				exec.setCelular(celular);
				exec.setCep(cep);
				exec.setRua(rua);
				exec.setComp(comp);
				exec.setBairro(bairro);
				exec.setCidade(cidade);
				exec.setEstado(estado);
				exec.setNum(num);
				exec.setAniversario(aniversario);

				profissionaisDB.save(exec);
				tableLoad();

			}
		});
		btnNovo.setBounds(126, 555, 115, 36);
		frame.getContentPane().add(btnNovo);

		// alterar registro
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nomeCompleto = textNomeCompleto.getText();
				String apelido = textNomeApelido.getText();
				String aniversario = textAniver.getText();
				String celular = textCel.getText();
				String cep = textCep.getText();
				String rua = textRua.getText();
				String comp = textComp.getText();
				int num = Integer.parseInt(textNum.getText());
				String bairro = textBairro.getText();
				String cidade = textCidade.getText();
				String estado = textEstado.getText();

				exec.setNomeCompleto(nomeCompleto);
				exec.setApelido(apelido);
				exec.setCelular(celular);
				exec.setCep(cep);
				exec.setRua(rua);
				exec.setComp(comp);
				exec.setBairro(bairro);
				exec.setCidade(cidade);
				exec.setEstado(estado);
				exec.setNum(num);
				exec.setAniversario(aniversario);

				profissionaisDB.update(exec, textLocalizar.getText());
				tableLoad();
			}
		});
		btnAlterar.setBounds(267, 555, 115, 36);
		frame.getContentPane().add(btnAlterar);

		// botao de limpar linhas
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNomeCompleto.setText("");
				textNomeApelido.setText("");
				textCel.setText("");
				textAniver.setText("");
				textCep.setText("");
				textRua.setText("");
				textNum.setText("");
				textComp.setText("");
				textBairro.setText("");
				textCidade.setText("");
				textEstado.setText("");
				textLocalizar.setText("");

				textNomeCompleto.requestFocus();

			}
		});
		btnLimpar.setBounds(406, 555, 115, 36);
		frame.getContentPane().add(btnLimpar);

		// Deletar
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profissionaisDB.delete(textLocalizar.getText());

				// limpando campos
				textNomeCompleto.setText("");
				textNomeApelido.setText("");
				textCel.setText("");
				textAniver.setText("");
				textCep.setText("");
				textRua.setText("");
				textNum.setText("");
				textComp.setText("");
				textBairro.setText("");
				textCidade.setText("");
				textEstado.setText("");

				tableLoad();
			}
		});
		btnDeletar.setBounds(543, 555, 115, 36);
		frame.getContentPane().add(btnDeletar);

		// Localizar
		JPanel panelLocalizar = new JPanel();
		panelLocalizar.setForeground(Color.BLACK);
		panelLocalizar.setBorder(new TitledBorder(null, "Localizar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLocalizar.setBounds(235, 444, 304, 71);
		frame.getContentPane().add(panelLocalizar);
		panelLocalizar.setLayout(null);

		// evento para localizar dados
		textLocalizar = new JTextField();
		textLocalizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					String nome_completo = textLocalizar.getText();
					Connection con = (Connection) DriverManager
							.getConnection("jdbc:mysql://localhost:3306/profissionais", "root", "");
					PreparedStatement pst = (PreparedStatement) con.prepareStatement(
							"SELECT nome_completo, apelido, celular, aniversario, cep, rua, numero, comp, bairro, cidade, estado from cadastro_profissionais WHERE nome_completo = ?");
					pst.setString(1, nome_completo);
					ResultSet rs = pst.executeQuery();

					if (rs.next() == true) {

						String nomeCompleto = rs.getString(1);
						String apelido = rs.getString(2);
						String celular = rs.getString(3);
						String aniversario = rs.getString(4);
						String cep = rs.getString(5);
						String rua = rs.getString(6);
						String num = rs.getString(7);
						String comp = rs.getString(8);
						String bairro = rs.getString(9);
						String cidade = rs.getString(10);
						String estado = rs.getString(11);

						textNomeCompleto.setText(nomeCompleto);
						textNomeApelido.setText(apelido);
						textCel.setText(celular);
						textAniver.setText(aniversario);
						textCep.setText(cep);
						textRua.setText(rua);
						textNum.setText(num);
						textComp.setText(comp);
						textBairro.setText(bairro);
						textCidade.setText(cidade);
						textEstado.setText(estado);

					} else {
						textNomeCompleto.setText("");
						textNomeApelido.setText("");
						textCel.setText("");
						textAniver.setText("");
						textCep.setText("");
						textRua.setText("");
						textNum.setText("");
						textComp.setText("");
						textBairro.setText("");
						textCidade.setText("");
						textEstado.setText("");
					}
				}

				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		textLocalizar.setBounds(72, 29, 207, 23);
		panelLocalizar.add(textLocalizar);
		textLocalizar.setColumns(10);

		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(16, 31, 61, 14);
		panelLocalizar.add(lblNome);

		// CONFIGURANDO TABELA

		// filtro de dados da tabela
		textLocalizar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTable();
			}
		});

		// Adcionando ouvinte de seleção à tabela para levar texto JTextField
		tabela.getSelectionModel().addListSelectionListener(e -> {
			// Verifique se alguma linha está selecionada
			if (tabela.getSelectedRow() != -1) {
				// Obtenha o valor da coluna "nome_completo" da linha selecionada
				String nomeCompleto = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();

				// Use o valor obtido para preencher o campo textLocalizar
				textLocalizar.setText(nomeCompleto);
			}
		});

		// Adicione um ouvinte de seleção à tabela
		tabela.getSelectionModel().addListSelectionListener(e -> {
			// Verifique se alguma linha está selecionada
			if (tabela.getSelectedRow() != -1) {
				// Obtenha o valor da coluna "nome_completo" da linha selecionada
				String nomeCompleto = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();

				// Use o valor obtido para buscar as informações correspondentes no banco de
				// dados
				// e definir os campos de texto
				try {
					Connection con = (Connection) DriverManager
							.getConnection("jdbc:mysql://localhost:3306/profissionais", "root", "");
					PreparedStatement pst = (PreparedStatement) con
							.prepareStatement("SELECT * FROM cadastro_profissionais WHERE nome_completo = ?");
					pst.setString(1, nomeCompleto);
					ResultSet rs = pst.executeQuery();

					// Verifique se o resultado contém dados
					if (rs.next()) {
						textNomeCompleto.setText(rs.getString("nome_completo"));
						textNomeApelido.setText(rs.getString("apelido"));
						textCel.setText(rs.getString("celular"));
						textAniver.setText(rs.getString("aniversario"));
						textCep.setText(rs.getString("cep"));
						textRua.setText(rs.getString("rua"));
						textNum.setText(rs.getString("numero"));
						textComp.setText(rs.getString("comp"));
						textBairro.setText(rs.getString("bairro"));
						textCidade.setText(rs.getString("cidade"));
						textEstado.setText(rs.getString("estado"));
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		// deixando a tabela imutável
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// Definindo todas as células como não editáveis
				return false;
			}
		};
		tabela.setModel(model);

	}// chave final do incializador

	private void filterTable() {
		try {
			String localizaLetra = textLocalizar.getText();
			// Consulta SQL para filtrar a tabela com base na inicial do nome completo
			String query = "SELECT nome_completo FROM cadastro_profissionais WHERE nome_completo LIKE ? ORDER BY nome_completo ASC";
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/profissionais",
					"root", "");
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(query);
			pst.setString(1, localizaLetra + "%"); // Adiciona % apenas após a inicial
			ResultSet rs = pst.executeQuery();
			tabela.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private JSONObject buscarCEP(String cep) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				response.append(line);
			}

			conn.disconnect();

			return new JSONObject(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
