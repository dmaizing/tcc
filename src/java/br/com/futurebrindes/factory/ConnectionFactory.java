package br.com.futurebrindes.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConnectionFactory {
        
        /* 
        *  Classe para conexão ao banco de dados
        *  MySQL
        *  usuário e senha padrões (somente durante os testes) ...
        */
    
        String driver = "com.mysql.jdbc.Driver";
        private String url = "jdbc:mysql://localhost:3306/futurebrindes?zeroDateTimeBehavior=convertToNull";
        //private String url = "jdbc:mysql://localhost:3306/futurebrindes";
        private String usuario = "root";
	private String senha   = ""; 
        
	public Connection getConnetion()  {
		try {
			Class.forName(driver);
			return  DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException erro) {
                        
			throw new RuntimeException(erro);		
		  }
		catch (ClassNotFoundException erro) {
			throw new RuntimeException(erro);		
		  }
	}
        
        
    
}
