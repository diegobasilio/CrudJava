package br.com.estetica.profissionaisDAO;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

import br.com.estetica.factory.ConnectionFactory;
import br.com.estetica.model.InfoProfissionais;
import br.com.estetica.model.TesteData;

public class ComandosBancoProfissionais {
	// Lugar para fazer o crud

	public void save(InfoProfissionais prof) {
		String insert = "insert into cadastro_profissionais(nome_completo, apelido, celular, aniversario, cep, rua, numero, comp, bairro, cidade, estado) values(?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;

		// prepara estrutura para executar
		PreparedStatement pstm = null;

		try {
			// tentando realizar conexao com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			// adicionando string no cursor para executar no mysql
			pstm = (PreparedStatement) conn.prepareStatement(insert);
			// guardando valor no sql
			pstm.setString(1, prof.getNomeCompleto());
			pstm.setString(2, prof.getApelido());
			pstm.setString(3, prof.getCelular());
			TesteData data = new TesteData();
			data.setData(prof.getAniversario());
			boolean hasError = data.getData(); // Armazena o resultado do método getData()

			if (hasError) {
			} else {
				pstm.setString(4, prof.getAniversario());
				// Restante do código
				if (prof.getCep().length() != 8) {
					JOptionPane.showMessageDialog(null, "CEP digitado de maneira incorreta. Tente: (xxxxxxxx)");
				} else {
					pstm.setString(5, prof.getCep());
					pstm.setString(6, prof.getRua());
					pstm.setInt(7, prof.getNum());
					pstm.setString(8, prof.getComp());
					pstm.setString(9, prof.getBairro());
					pstm.setString(10, prof.getCidade());
					if (prof.getEstado().length() == 2) {
						pstm.setString(11, prof.getEstado());
						JOptionPane.showMessageDialog(null, "Profissional adicionado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Estado tem menos ou mais que 2 caracteres");
					}
				}
			}

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// finalizando o cursor
			try {
				if (pstm != null) {
					pstm.close();
				} else if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void update(InfoProfissionais prof, String nome_completo) {
		String update = "UPDATE cadastro_profissionais set nome_completo = ?, apelido = ?, celular = ?, aniversario = ?, cep = ?, rua = ?, numero = ?, comp = ?, bairro = ?, cidade = ?, estado = ? where nome_completo = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(update);
			pstm.setString(1, prof.getNomeCompleto());
			pstm.setString(2, prof.getApelido());
			pstm.setString(3, prof.getCelular());
			if (prof.getAniversario().length() != 10) {
				JOptionPane.showMessageDialog(null, "Digite o aniversário desse modelo: tente:\n(dd/mm/yyyy)");
			} else {
				pstm.setString(4, prof.getAniversario());
				if (prof.getCep().length() != 8) {
					JOptionPane.showMessageDialog(null, "CEP digitado de maneira incorreta. tente:\n(xxxxxxxx)");
				} else {
					pstm.setString(5, prof.getCep());
					pstm.setString(6, prof.getRua());
					pstm.setInt(7, prof.getNum());
					pstm.setString(8, prof.getComp());
					pstm.setString(9, prof.getBairro());
					pstm.setString(10, prof.getCidade());
					if (prof.getEstado().length() == 2) {
						pstm.setString(11, prof.getEstado());
						pstm.setString(12, nome_completo);
						JOptionPane.showMessageDialog(null, "Profissional editado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Estado tem menos ou mais que 2 caracteres");
					}
				}
			}

			// executando

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// finalizando o cursor
			try {
				if (pstm != null) {
					pstm.close();
				} else if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	public void delete(String nome_completo) {

		String deletar = "DELETE from cadastro_profissionais where nome_completo = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(deletar);
			pstm.setString(1, nome_completo);
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				} else if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
