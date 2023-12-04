package br.com.fiap.ddd.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.domain.Clinica;

public class ClinicaDAO {
	private Connection conn;

	public ClinicaDAO(Connection connection) {
		this.conn = connection;
	}

	public void salvar(Clinica clinica, int idAtend) {
		String sql = "INSERT INTO t_hf_clinica(id_clinica, T_HF_ATEND_ID_ATEND, nm_nome, nm_cep, nm_telefone) VALUES(sq_clinica.NEXTVAL, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, idAtend);
			preparedStatement.setString(2, clinica.getNome());
			preparedStatement.setString(3, clinica.getCep());
			preparedStatement.setString(4, clinica.getTelefone());

			preparedStatement.execute();
			preparedStatement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Clinica consultar(int id) {
		String sql = "SELECT * FROM t_hf_clinica WHERE id_clinica = ?";

		PreparedStatement ps;
		ResultSet rs;
		Clinica clinica = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int idClinicaRS = rs.getInt(1);
				String nomeRS = rs.getString(2);
				String cepRS = rs.getString(3);
				String telefoneRS = rs.getString(4);

				clinica = new Clinica(nomeRS, telefoneRS, cepRS, idClinicaRS);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return clinica;
	}
	
	public void atualizarClinica(Clinica clinica, int id) {
		String sql = "UPDATE t_hf_clinica SET nm_nome = ?, nm_telefone = ?, nm_cep = ? WHERE id_clinica = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, clinica.getNome());
			preparedStatement.setString(2, clinica.getTelefone());
			preparedStatement.setString(3, clinica.getCep());
			preparedStatement.setInt(4, id);
			
			preparedStatement.execute();
			preparedStatement.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	public void deletarClinica(int id) {
		String sql = "DELETE FROM t_hf_clinica WHERE id_clinica = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			preparedStatement.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}
