package br.com.fiap.ddd.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.domain.Atendimento;

public class AtendimentoDAO {

private Connection conn;
	
	public AtendimentoDAO(Connection connection) {
		this.conn = connection;
	}
	
	public void salvar(Atendimento atendimento, int idFila) {
		String sql = "INSERT INTO t_hf_atend(id_atend, T_HF_FILA_ID_FILA, dt_atend, nm_tipo_atend, ds_atend, st_status, nm_servico) "
				+ "VALUES(sq_atend.NEXTVAL, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, idFila);
			preparedStatement.setString(2, atendimento.getDtAtendimento());
			preparedStatement.setString(3, atendimento.getTipo());
			preparedStatement.setString(4, atendimento.getDescricao());
			preparedStatement.setString(5, atendimento.getStatus());
			preparedStatement.setString(6, atendimento.getServico());
			
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
	
	public Atendimento consultar(int id){
		String sql = "SELECT * FROM t_hf_atend WHERE id_atend = ?";
		
		PreparedStatement ps;
		ResultSet rs;
		Atendimento atendimento = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int idAtendRS = rs.getInt(1);
				String tipoRS = rs.getString(3);
				String descRS = rs.getString(4);
				String statusRS = rs.getString(5);
				String servicoRS = rs.getString(7);
				
				atendimento = new Atendimento(tipoRS, descRS, statusRS, servicoRS, idAtendRS);
				
			}
			rs.close();
			ps.close();
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
		
		return atendimento;
	}
	
	public void atualizarAtendimento(Atendimento atendimento, int id) {
		String sql = "UPDATE t_hf_atend SET dt_atend = TO_DATE(?, 'DD/MM/YYYY'), nm_tipo_atend = ?, ds_atend = ?, st_status = ?, nm_servico = ? WHERE id_atend = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, atendimento.getDtAtendimento());
			preparedStatement.setString(2, atendimento.getTipo());
			preparedStatement.setString(3, atendimento.getDescricao());
			preparedStatement.setString(4, atendimento.getStatus());
			preparedStatement.setString(5, atendimento.getServico());
			preparedStatement.setInt(6, id);
			
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
	
	public void deletarAtendimento(int id) {
		String sql = "DELETE FROM t_hf_atend WHERE id_atend = ?";
		
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
