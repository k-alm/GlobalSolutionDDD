package br.com.fiap.ddd.gs.services;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.connection.ConnectionFactory;
import br.com.fiap.ddd.gs.dao.AtendimentoDAO;
import br.com.fiap.ddd.gs.domain.Atendimento;
import br.com.fiap.ddd.gs.dto.AtendimentoDTO;
import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class AtendimentoServices {
	
	private ConnectionFactory connection;

	public AtendimentoServices() {
        this.connection = new ConnectionFactory();
    }
    
    public void cadastrarAtendimento(AtendimentoDTO atendimentoDto, int idFila) {
    	Connection conn = connection.recuperarConexao();
    	AtendimentoServices fService = new AtendimentoServices();
    	
    	Atendimento atendimento = transformarEmEntidade(atendimentoDto);
    	
    	if(fService.consultarAtendimento(idFila) == null) {
    		throw new RegraDeNegocioException("Não existe nenhuma atendimento com o id informado para relacionar ao cadastro.");
    	}
    	
    	if(atendimento == null) {    		
    		throw new RegraDeNegocioException("O atendimento está nulo");
    	}
    	
    	new AtendimentoDAO(conn).salvar(atendimento, idFila);
    }
    
    public Atendimento consultarAtendimento(int id){
    	Connection conn = connection.recuperarConexao();
    	
    	Atendimento atendimento = new AtendimentoDAO(conn).consultar(id);
    	
    	validarAtendimento(id);
    	
    	return atendimento;
    }
    
    public void atualizarAtendimento(AtendimentoDTO atendimentoDto, int id) {
    	Connection conn = connection.recuperarConexao();
    	
		Atendimento atendimento = transformarEmEntidade(atendimentoDto);
    	
    	validarAtendimento(id);
		
    	if(!atendimento.getServico().equals("Hospital") || !atendimento.getServico().equals("Clinica")) {
    		throw new RegraDeNegocioException("O serviço do atendimento deve ser ou Hospital ou Clinica");
    	}
    	
    	new AtendimentoDAO(conn).atualizarAtendimento(atendimento, id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void deletarAtendimento(int id) {
    	Connection conn = connection.recuperarConexao();
    	
    	validarAtendimento(id);
    	
    	new AtendimentoDAO(conn).deletarAtendimento(id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void validarAtendimento(int id) {
    	if(consultarAtendimento(id) == null) {
    		throw new RegraDeNegocioException("O atendimento informado não existe");
    	}
    }
    
    private Atendimento transformarEmEntidade(AtendimentoDTO atendimentoDto) {
    	Atendimento atendimento = new Atendimento(atendimentoDto.getTipo(), atendimentoDto.getDescricao(), atendimentoDto.getStatus(), atendimentoDto.getServico(), atendimentoDto.getId());
    	return atendimento;
    }
}
