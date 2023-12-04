package br.com.fiap.ddd.gs.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {
	public Connection recuperarConexao() {
		try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        
        config.setJdbcUrl("");/*URL do banco de dados*/
        config.setUsername("");/*Usuário*/
        config.setPassword("");/*Senha*/
        config.setMaximumPoolSize(10);
        
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        
        return new HikariDataSource(config);
    }
}
