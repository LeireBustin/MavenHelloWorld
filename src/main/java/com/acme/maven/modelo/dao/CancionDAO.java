package com.acme.maven.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.acme.maven.modelo.ConnectionManager;
import com.acme.maven.modelo.pojo.Cancion;

public class CancionDAO implements Persistable<Cancion> {

			
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
			
	
	/**
	 * Busca cancuines q coincidadn el nombre o artista de a cancion
	 * No es casesensitibe.<br>
	 * @param criterio string cadena a buscar
	 * @return Listado de canciones limitado por LIMIT, vacio si no
	 */
	public List<Cancion> findByNameOrArtist( String criterio) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		String sql = "SELECT `id`, `nombre`, `artista`, `duracion`, `cover` FROM `spoty`.`cancion` WHERE  `nombre` LIKE ? OR `artista` LIKE ? ORDER BY `id` DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + criterio + "%");
			pst.setString(2, "%" + criterio + "%");
			pst.setInt(3, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {
				Cancion c;
				while (rs.next()) {
					c = mapeo(rs);
					canciones.add(c);
					c = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return canciones;
				
	}
	
	@Override
	public List<Cancion> findAll() {
		
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		String sql = "SELECT `id`, `nombre`, `artista`, `duracion`, `cover` FROM `spoty`.`cancion` ORDER BY `id`=? DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			/*	//ANTES SIN ConnectionManager:
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			*/
			pst.setInt(1, Persistable.LIMIT);
			
			try (ResultSet rs = pst.executeQuery();) {
				Cancion c;
				while (rs.next()) {
					c = mapeo(rs);
					canciones.add(c);
					c = null;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}

		return canciones;
	}
	

	@Override
	public boolean create(Cancion c) {
		boolean resul = false;
		String sql = "INSERT INTO `spoty`.`cancion` (`nombre`, `artista` , `duracion` , `cover`) VALUES (?, ?, ?, ?);";
		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		) {

			pst.setString(1, c.getNombre());
			pst.setString(2, c.getArtista());
			pst.setString(3, c.getDuracion());
			pst.setString(4, c.getCover());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				try (ResultSet keys = pst.getGeneratedKeys();) {
					while (keys.next()) {
						c.setId(keys.getInt(1));
					}
					resul = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return resul;
	}

	@Override
	public boolean update(Cancion c, int id) {
		boolean resul = false;
		String sql = "UPDATE `spoty`.`cancion` SET `nombre`='?', `artista`='?', `duracion`='?', `cover`='?' WHERE  `id`=?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, c.getNombre());
			pst.setString(2, c.getArtista());
			pst.setString(3, c.getDuracion());
			pst.setString(4, c.getCover());
			pst.setInt(5, id);
			//Si me devuelve un 1 es q lo  he hecho bien
			if (pst.executeUpdate() == 1) {
				resul = true; //todo OK
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return resul;
	}

	
	@Override
	public boolean delete(int id) {
		boolean resul = false;
		String sql = "DELETE FROM `spoty`.`cancion` WHERE  `id`=?;";
		try (

				Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql);

		) {

			pst.setInt(1, id);
			if (pst.executeUpdate() == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}
	
	
	/**
	 * @return retorna un pojo si lo encuentra, null si no.
	 */
	@Override
	public Cancion findById(int id) {
		Cancion c = null;
		String sql = "SELECT `id`, `nombre`, `artista`, `duracion`, `cover` FROM `spoty`.`cancion` WHERE  `id`=?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			/*antes		pst.setInt(1, c.getId());	*/
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					c = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return c;
	}
	
	//HAY QUE MAPEAR (pasar datos  a un objeto) DE UN RESULTSET A UN OBJETO TIPO CANCION
	/*******de un resultSet me mapea a un Pojo Cancion***
	 * Mapea de un ResultSet a un objeto de tipo Cancion
	 *
	 * @param rs
	 *            ResultSet
	 * @return Cancion o null si falla
	 * @throws SQLException
	 */
	Cancion mapeo(ResultSet rs) throws SQLException {
		Cancion c = null;
		if (rs != null) {
			c = new Cancion();
			c.setId(rs.getInt("id"));
			c.setNombre(rs.getString("nombre"));
			c.setArtista(rs.getString("artista"));
			c.setDuracion(rs.getString("duracion"));
			c.setCover(rs.getString("cover"));
		}
		return c;
	}
	
}
