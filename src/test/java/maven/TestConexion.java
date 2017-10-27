package maven;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;




public class TestConexion {
	
	//Variables globales
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/spoty"; //jdbc:mysql://192.234.21.47:3306/test\\r\\n\"
	//jdbc:mysql://192.234.21.47:3306/test

	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	/**
	 * Comprobar que esté libreria para MySQL
	 */
	@Test
	public void testDriver() {
		
		try {
			Class.forName(DRIVER);
			assertTrue(true);
		}catch (ClassNotFoundException e) {
			fail("tarlarriiii error");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Comprobar que esté libreria para MySQL
	 */
	@Test
	public void testConexion() {
		
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			if(con!=null)
			assertTrue(true);
		}catch (SQLException e) {
			fail(String.format("No se puede establecer la conexion para el usuario = %s , usuario = %s , usuario = %s.", URL, USER, PASSWORD));
			e.printStackTrace();
		}
	}
	
	/**
	 * Comprobar que esté libreria para MySQL
	 * 
	 * /**
	 * Insertamos una nueva cancion con executeUpdate <br>
	 * Recuperamos la ultima cancion insertada executeQuery <br>
	 * Comprobamos que sean los mismos datos
	 */
	@Test
	public void testInsertarCancion() {
		
		try {
			////Cancion de prueba
			String nombre ="Whatever";
			String artista ="Oasis";
			String duracion ="6:00";
			
			//establecer conexion
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//SQL insert y crear PreparedStatement	
			String sqlInsert = "INSERT INTO `spoty`.`cancion` (`nombre`, `artista`, `duracion`) VALUES (? , ? , ? );";
			
			PreparedStatement pst = con.prepareStatement(sqlInsert);
			
			//preparar Statement
			pst.setString(1, nombre);
			pst.setString(2, artista);
			pst.setString(3, duracion);
			
			//executeUpdate
			int affectedRows = pst.executeUpdate();
			assertEquals(1, affectedRows);
			
			//consultar ultimo registro insertado
			String sqlQuery = "select `id`, `nombre`, `artista`, `duracion`, `cover` from `cancion` order by `id` desc limit 1";
			PreparedStatement pst2 = con.prepareStatement(sqlQuery);
			
			//obtener resultados
			ResultSet rs = pst2.executeQuery();
			
			//iterar sobre ellos
			while( rs.next() ) {
				
				assertEquals(nombre, rs.getString("nombre"));
				assertEquals(artista, rs.getString("artista"));
				assertEquals(duracion, rs.getString("duracion"));
			}
			
		}catch (SQLException e) {
			fail( e.getMessage() );
			e.printStackTrace();
		}
	}
	
}
