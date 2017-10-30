package com.acme.maven.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements AutoCloseable {
	
	//Variables globales DATOS DE CONFIGURACION
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/spoty";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";
	
	private static Connection con;
	
	public static Connection open () throws SQLException, ClassNotFoundException {
		//cargar el driver
		Class.forName(DRIVER);
		//establecer conexion
		con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		return con;
	}

	@Override
	public void close() throws Exception {
		
		if (con != null) {
			con.close();
			
		}
		
		
	}
	

}
