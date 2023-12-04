package br.com.fiap.ddd.gs.services;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.connection.ConnectionFactory;
import br.com.fiap.ddd.gs.dao.FilaDAO;
import br.com.fiap.ddd.gs.domain.Fila;
import br.com.fiap.ddd.gs.dto.FilaDTO;
import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class FilaServices {

	private ConnectionFactory connection;

	public FilaServices() {
        this.connection = new ConnectionFactory();
    }
    
    public void cadastrarFila(FilaDTO filaDto, int idCliente) {
    	Connection conn = connection.recuperarConexao();
    	FilaServices cService = new FilaServices();
    	
    	Fila fila = transformarEmEntidade(filaDto);
    	
    	if(cService.consultarFila(idCliente) == null) {
    		throw new RegraDeNegocioException("Não existe nenhum cliente com o id informado para relacionar ao cadastro.");
    	}
    	
    	if(fila == null) {    		
    		throw new RegraDeNegocioException("A fila está nula");
    	}
    	
    	new FilaDAO(conn).salvar(fila, idCliente);
    }
    
    public Fila consultarFila(int id){
    	Connection conn = connection.recuperarConexao();
    	
    	Fila fila = new FilaDAO(conn).consultar(id);
    	
    	validarFila(id);
    	
    	return fila;
    }
    
    public void atualizarFila(FilaDTO filaDto, int id) {
    	Connection conn = connection.recuperarConexao();
    	
		Fila fila = transformarEmEntidade(filaDto);
    	
    	validarFila(id);
		
    	new FilaDAO(conn).atualizarFila(fila, id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void deletarFila(int id) {
    	Connection conn = connection.recuperarConexao();
    	
    	validarFila(id);
    	
    	new FilaDAO(conn).deletarFila(id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void validarFila(int id) {
    	if(consultarFila(id) == null) {
    		throw new RegraDeNegocioException("A fila informada não existe");
    	}
    }
    
    private Fila transformarEmEntidade(FilaDTO filaDto) {
    	Fila fila = new Fila(filaDto.getId(), filaDto.getPreferencial());
    	return fila;
    }
    
}
