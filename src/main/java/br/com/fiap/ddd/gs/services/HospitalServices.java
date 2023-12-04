package br.com.fiap.ddd.gs.services;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.ddd.gs.connection.ConnectionFactory;
import br.com.fiap.ddd.gs.dao.HospitalDAO;
import br.com.fiap.ddd.gs.domain.Hospital;
import br.com.fiap.ddd.gs.dto.HospitalDTO;
import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class HospitalServices {
	
	private ConnectionFactory connection;

	public HospitalServices() {
        this.connection = new ConnectionFactory();
    }
    
    public void cadastrarHospital(HospitalDTO hospitalDto, int idAtend) {
    	Connection conn = connection.recuperarConexao();
    	AtendimentoServices aService = new AtendimentoServices();
    	
    	Hospital hospital = transformarEmEntidade(hospitalDto);
    	
    	if(aService.consultarAtendimento(idAtend) == null) {
    		throw new RegraDeNegocioException("Não existe nenhum atendimento com o id informado para relacionar ao cadastro.");
    	}
    	else if(!aService.consultarAtendimento(idAtend).getServico().equals("Hospital")) {
    		throw new RegraDeNegocioException("O serviço do atendimento não é um Hospital");
    	}
    	
    	new HospitalDAO(conn).salvar(hospital, idAtend);
    }
    
    public Hospital consultarHospital(int id){
    	Connection conn = connection.recuperarConexao();
    	
    	Hospital hospital = new HospitalDAO(conn).consultar(id);
    	
    	validarHospital(id);
    	
    	return hospital;
    }
    
    public void atualizarHospital(HospitalDTO hospitalDto, int id) {
    	Connection conn = connection.recuperarConexao();
    	
		Hospital hospital = transformarEmEntidade(hospitalDto);
    	
    	validarHospital(id);
		
    	new HospitalDAO(conn).atualizarHospital(hospital, id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void deletarHospital(int id) {
    	Connection conn = connection.recuperarConexao();
    	
    	validarHospital(id);
    	
    	new HospitalDAO(conn).deletarHospital(id);

    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void validarHospital(int id) {
    	if(consultarHospital(id) == null) {
    		throw new RegraDeNegocioException("A hospital informada não existe");
    	}
    }
    
    private Hospital transformarEmEntidade(HospitalDTO hospitalDto) {
    	Hospital hospital = new Hospital(hospitalDto.getNome(), hospitalDto.getTelefone(), hospitalDto.getCep(), hospitalDto.getId());
    	return hospital;
    }
    
    
}
