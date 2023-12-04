package br.com.fiap.ddd.gs.services;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.connection.ConnectionFactory;
import br.com.fiap.ddd.gs.dao.ClienteDAO;
import br.com.fiap.ddd.gs.domain.Cliente;
import br.com.fiap.ddd.gs.dto.ClienteDTO;
import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class ClienteServices {

	private ConnectionFactory connection;

    public ClienteServices() {
        this.connection = new ConnectionFactory();
    }
    
    public void cadastrarCliente(ClienteDTO clienteDto){
    	try(Connection conn = connection.recuperarConexao()){
    		Cliente cliente = transformarEmEntidade(clienteDto);
    		
    		if(consultarClientePorEmail(cliente.getEmail()) != null) {
    			throw new RegraDeNegocioException("Já existe um cliente com o email informado");
    		}
    		
    		new ClienteDAO(conn).salvar(cliente);
    	}catch(SQLException e) {
    		throw new RuntimeException(e);
    	}
		
    }
    
    public void atualizarCliente(ClienteDTO clienteDto, int id) {
    	Connection conn = connection.recuperarConexao();
    	
		Cliente cliente = transformarEmEntidade(clienteDto);
    	
    	validarCliente(id);
		
    	new ClienteDAO(conn).atualizarCliente(cliente, id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void deletarCliente(int id) {
    	Connection conn = connection.recuperarConexao();
    	
    	validarCliente(id);
    	
    	new ClienteDAO(conn).deletarCliente(id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public Cliente consultarCliente(int id){
    	Connection conn = connection.recuperarConexao();
    	
    	Cliente cliente = new ClienteDAO(conn).consultar(id);
    	
    	validarCliente(id);
    	
    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return cliente;
    }
    
    public Cliente consultarClientePorEmail(String email) {
    	Connection conn = connection.recuperarConexao();
    	
    	Cliente cliente = new ClienteDAO(conn).consultarPorEmail(email);
    	
    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return cliente;
    }
    
    private void validarCliente(int id) {
    	if(consultarCliente(id) == null) {
    		throw new RegraDeNegocioException("O cliente informado não existe");
    	}
    }
    
    private Cliente transformarEmEntidade(ClienteDTO clienteDto) {
    	Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getTelefone(), clienteDto.getEmail(), clienteDto.getCep(), clienteDto.getIdade(), clienteDto.getId());
    	return cliente;
    }
}
