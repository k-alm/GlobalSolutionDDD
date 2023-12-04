package br.com.fiap.ddd.gs.services;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.connection.ConnectionFactory;
import br.com.fiap.ddd.gs.dao.ClinicaDAO;
import br.com.fiap.ddd.gs.domain.Clinica;
import br.com.fiap.ddd.gs.dto.ClinicaDTO;
import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class ClinicaServices {
	
	private ConnectionFactory connection;

	public ClinicaServices() {
        this.connection = new ConnectionFactory();
    }
    
    public void cadastrarClinica(ClinicaDTO clinicaDto, int idAtend) {
    	Connection conn = connection.recuperarConexao();
    	AtendimentoServices aService = new AtendimentoServices();
    	
    	Clinica clinica = transformarEmEntidade(clinicaDto);
    	
    	if(aService.consultarAtendimento(idAtend) == null) {
    		throw new RegraDeNegocioException("Não existe nenhum atendimento com o id informado para relacionar ao cadastro.");
    	}
    	else if(!aService.consultarAtendimento(idAtend).getServico().equals("Clinica")) {
    		throw new RegraDeNegocioException("O serviço do atendimento não é uma Clinica");
    	}
    	
    	
    	if(clinica == null) {
    		throw new RegraDeNegocioException("A clinica está nula");
    	}
    	
    	new ClinicaDAO(conn).salvar(clinica, idAtend);
    }
    
    public Clinica consultarClinica(int id){
    	Connection conn = connection.recuperarConexao();
    	
    	Clinica clinica = new ClinicaDAO(conn).consultar(id);
    	
    	validarClinica(id);
    	
    	return clinica;
    }
    
    public void atualizarClinica(ClinicaDTO clinicaDto, int id) {
    	Connection conn = connection.recuperarConexao();
    	
		Clinica clinica = transformarEmEntidade(clinicaDto);
    	
    	validarClinica(id);
		
    	new ClinicaDAO(conn).atualizarClinica(clinica, id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void deletarClinica(int id) {
    	Connection conn = connection.recuperarConexao();
    	
    	validarClinica(id);
    	
    	new ClinicaDAO(conn).deletarClinica(id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void validarClinica(int id) {
    	if(consultarClinica(id) == null) {
    		throw new RegraDeNegocioException("A clinica informada não existe");
    	}
    }
    
    private Clinica transformarEmEntidade(ClinicaDTO clinicaDto) {
    	Clinica clinica = new Clinica(clinicaDto.getNome(), clinicaDto.getTelefone(), clinicaDto.getCep(), clinicaDto.getId());
    	return clinica;
    }
}
