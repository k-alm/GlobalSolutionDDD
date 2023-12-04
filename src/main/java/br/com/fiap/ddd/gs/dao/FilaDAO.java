package br.com.fiap.ddd.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.domain.Fila;

public class FilaDAO {
	
	private Connection conn;
	
	public FilaDAO(Connection connection) {
		this.conn = connection;
	}
	
	public void salvar(Fila fila, int idCliente) {
		String sql = "INSERT INTO t_hf_fila(id_fila, T_HF_CLIENTE_ID_CLIENTE, nm_senha, st_preferencial) VALUES(sq_fila.NEXTVAL, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, idCliente);
			preparedStatement.setString(2, fila.getSenha());
			preparedStatement.setString(3, fila.getPreferencial());
			
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
	
	public Fila consultar(int id){
		String sql = "SELECT * FROM t_hf_fila WHERE id_fila = ?";
		
		PreparedStatement ps;
		ResultSet rs;
		Fila fila = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int idFilaRS = rs.getInt(1);
				String senhaRS = rs.getString(2);
				
				fila = new Fila(idFilaRS, senhaRS);
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
		
		return fila;
	}
	
	public void atualizarFila(Fila fila, int id) {
		String sql = "UPDATE t_hf_fila SET st_preferencial = ? WHERE id_fila = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, fila.getPreferencial());
			preparedStatement.setInt(2, id);
			
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
	
	public void deletarFila(int id) {
		String sql = "DELETE FROM t_hf_fila WHERE id_fila = ?";
		
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
