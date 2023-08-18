package br.com.estetica.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/profissionais";
	
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// conectando o java ao banco
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}

	public static void main(String[] args) throws Exception {
		Connection con = createConnectionToMySQL();
		
		if (con != null) {
			System.out.println("Conexão obetida com sucesso!");
			con.close();
		}
	}
}