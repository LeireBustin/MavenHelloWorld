package com.acme.maven.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.acme.maven.modelo.pojo.Cancion;
import com.mysql.jdbc.Statement;

public class CancionDAO implements Persistable<Cancion> {

	//Variables globales
			final String DRIVER = "com.mysql.jdbc.Driver";
			final String URL = "jdbc:mysql://localhost:3306/spoty";
			final String USUARIO = "root";
			final String PASSWORD = "";
			
	//Implementar Singleton: PATRON SINGLETON para que solo haya un objeto en el test *
	/***
	* Vamos a usar un patron <b>singleton</b> para exista una unica instancia de esta clase.
	* @see https://es.wikipedia.org/wiki/Singleton 
	*/

	private static CancionDAO INSTANCE = null;
	
	private CancionDAO() {
		super();
	}
	//acceso para la clase
	public synchronized static CancionDAO getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new CancionDAO();
		}
		return INSTANCE;
	}//--HASTA AQUI SINGLETON
			
	
	@Override
	public List<Cancion> findAll() {
		
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		try {
			
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			String sql = "SELECT `id`, `nombre`, `artista`, `duracion`, `cover` FROM `spoty`.`cancion` ORDER BY  `id`=?;";
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, Persistable.LIMIT);
			
			//TODO
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return c;
	}
	
	/**
	 * 
	 * 
	 * @return retorna un pojo si lo encuentra, null si no.
	 */
	@Override
	public Cancion findById(int id) {
		Cancion c = null;
		
		
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		String sql = "SELECT `id`, `nombre`, `artista`, `duracion`, `cover` FROM `spoty`.`cancion` WHERE  `id`=?;";
		PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pst.setInt(1, c.getId());
		

		//Si me devuelve un 1 es q lo  he hecho bien
		if (pst.executeUpdate() == 1) {
			resul = true; //todo OK
		}
		
		return null;
	}//TODO
	
	//HAY QUE MAPEAR (pasar datos  a un objeto) DE UN RESULTSET A UN OBJETO TIPO CANCION
	/**		*****de un resultSet me mapea a un Pojo Cancion***
	 * Mapea de un ResultSet a un objeto de tipo Cancion
	 * @param rs
	 * @return 
	 */
	Cancion mapeo(ResultSet rs) {
		Cancion c = null;
		if(rs != null) {
				c = new Cancion();
				c.setId(rs.next());/////////////7777
				c.setNombre(nombre);
				c.setArtista(artista);
		
		}
		return null;
	}//TODO

	@Override
	public boolean create(Cancion c) {
		boolean resul = false;
		try {
			//cargar el driver
			Class.forName(DRIVER);
			
			//establecer conexion
			Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			
			//Insert
			String sql = "INSERT INTO `spoty`.`cancion` (`nombre`, `artista` , `duracion` , `cover`) VALUES (?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, c.getNombre());
			pst.setString(2, c.getArtista());
			pst.setString(3, c.getDuracion());
			pst.setString(4, c.getCover());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				ResultSet keys = pst.getGeneratedKeys();
				while (keys.next()) {
					c.setId(keys.getInt(1));
				}
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean update(Cancion c, int id) {
		boolean resul = false;
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			String sql = "UPDATE `spoty`.`cancion` SET `nombre`='?', `artista`='?', `duracion`='?', `cover`='?' WHERE  `id`=?;";
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, c.getNombre());
			pst.setString(2, c.getArtista());
			pst.setString(3, c.getDuracion());
			pst.setString(4, c.getCover());
			pst.setInt(5, id);

			//Si me devuelve un 1 es q lo  he hecho bien
			if (pst.executeUpdate() == 1) {
				resul = true; //todo OK
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			String sql = "DELETE FROM `spoty`.`cancion` WHERE  `id`=?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			if (pst.executeUpdate() == 1) {
				resul = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}
	
	
	
}
