package br.com.fiap.ddd.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.domain.Cliente;

public class ClienteDAO {
	private Connection conn;
	
	public ClienteDAO(Connection connection) {
		this.conn = connection;
	}
	
	public void salvar(Cliente cliente) {
		String sql = "INSERT INTO t_hf_cliente(id_cliente, nm_nome, nm_email, nm_telefone, nm_cep, nr_idade) VALUES(sq_cliente.NEXTVAL, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getEmail());
			preparedStatement.setString(3, cliente.getTelefone());
			preparedStatement.setString(4, cliente.getCep());
			preparedStatement.setInt(5, cliente.getIdade());
			
			preparedStatement.execute();
			preparedStatement.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Cliente consultar(int id){
		String sql = "SELECT * FROM t_hf_cliente WHERE id_cliente = ?";
		
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String nomeRS = rs.getString(1);
				String emailRS = rs.getString(2);
				int idClienteRS = rs.getInt(3);
				String telefoneRS = rs.getString(4);
				String cepRS = rs.getString(5);
				int idadeRS = rs.getInt(6);

				cliente = new Cliente(nomeRS, telefoneRS, emailRS, cepRS, idadeRS, idClienteRS);
			}
			rs.close();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return cliente;
	}
	
	public Cliente consultarPorEmail(String email) {
		String sql = "SELECT * FROM t_hf_cliente WHERE nm_email = ?";
		
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String nomeRS = rs.getString(1);
				String emailRS = rs.getString(2);
				int idClienteRS = rs.getInt(3);
				String telefoneRS = rs.getString(4);
				String cepRS = rs.getString(5);
				int idadeRS = rs.getInt(6);

				cliente = new Cliente(nomeRS, telefoneRS, emailRS, cepRS, idadeRS, idClienteRS);
			}
			rs.close();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return cliente;
	}
	
	public void atualizarCliente(Cliente cliente, int id) {
		String sql = "UPDATE t_hf_cliente SET nm_nome = ?, nm_email = ?, nm_telefone = ?, nm_cep = ?, nr_idade = ? WHERE id_cliente = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getEmail());
			preparedStatement.setString(3, cliente.getTelefone());
			preparedStatement.setString(4, cliente.getCep());
			preparedStatement.setInt(5, cliente.getIdade());
			preparedStatement.setInt(6, id);
			
			preparedStatement.execute();
			preparedStatement.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void deletarCliente(int id) {
		String sql = "DELETE FROM t_hf_cliente WHERE id_cliente = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);			
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			preparedStatement.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
