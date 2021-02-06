package br.com.scp.dao;

import java.sql.*;

public class Conexao_SCP {
    
    // Método Responsavel por estabelecer a conexão com o banco 
    
    public static Connection conector() {
        
        java.sql.Connection conexao = null;
		
        // A linha abaixo "chama" o driver 

        String driver = "com.mysql.jdbc.Driver";

        // Armazenando informações referente ao banco 
        String url = "jdbc:mysql://127.0.0.1:3306/SCP?useSSL=false"; 
        String USUARIO = "gustavo";
        String SENHA = "123";
        
        // Estabelecendo a conexão com o banco de dados
        
        try {
            
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, USUARIO, SENHA);
            return conexao;
            
        } catch (Exception e) {
            
            // esclarecer o erro
            System.out.println(e);
            return null;
        }
    }
}
