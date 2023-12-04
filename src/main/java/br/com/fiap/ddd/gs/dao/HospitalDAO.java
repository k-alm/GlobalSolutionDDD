package br.com.fiap.ddd.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.domain.Hospital;

public class HospitalDAO {
	private Connection conn;

	public HospitalDAO(Connection connection) {
		this.conn = connection;
	}

	public void salvar(Hospital hospital, int idAtend) {
		String sql = "INSERT INTO t_hf_hospital(id_hospital, T_HF_ATEND_ID_ATEND, nm_nome, nm_cep, nm_telefone) VALUES(sq_hospital.NEXTVAL, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, idAtend);
			preparedStatement.setString(2, hospital.getNome());
			preparedStatement.setString(3, hospital.getCep());
			preparedStatement.setString(4, hospital.getTelefone());

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

	public Hospital consultar(int id) {
		String sql = "SELECT * FROM t_hf_hospital WHERE id_hospital = ?";

		PreparedStatement ps;
		ResultSet rs;
		Hospital hospital = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				int idHospitalRS = rs.getInt(1);
				String cepRS = rs.getString(2);
				String nomeRS = rs.getString(3);
				String telefoneRS = rs.getString(4);

				hospital = new Hospital(nomeRS, telefoneRS, cepRS, idHospitalRS);
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

		return hospital;
	}
	
	public void atualizarHospital(Hospital hospital, int id) {
		String sql = "UPDATE t_hf_hospital SET nm_nome = ?, nm_telefone = ?, nm_cep = ? WHERE id_hospital = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, hospital.getNome());
			preparedStatement.setString(2, hospital.getTelefone());
			preparedStatement.setString(3, hospital.getCep());
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
	
	public void deletarHospital(int id) {
		String sql = "DELETE FROM t_hf_hospital WHERE id_hospital = ?";
		
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
