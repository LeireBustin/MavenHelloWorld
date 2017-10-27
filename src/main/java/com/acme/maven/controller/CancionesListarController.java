package com.acme.maven.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acme.maven.modelo.dao.CancionDAO;
import com.acme.maven.modelo.pojo.Cancion;

/**
 * Servlet implementation class ListarController
 */
@WebServlet("/canciones/listar")
public class CancionesListarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String msg = "OK llegamos al JSP, primer paso HECHO";
		
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		/*	SIN DAO *******(BORRAR)
		//Variables globales
		final String DRIVER = "com.mysql.jdbc.Driver";
		final String URL = "jdbc:mysql://localhost:3306/spoty";
		final String USER = "root";
		final String PASSWORD = "";
		*/
		
		
		
		CancionDAO dao = CancionDAO.getInstance();

		//Conseguir canciones de la BBDD
		try {
			
			/*	SIN DAO *******(BORRAR)
			//cargar el driver
			Class.forName(DRIVER);
			
			//establecer conexion
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//Select
			String sqlQuery = "select `id`, `nombre`, `artista`, `duracion`, `cover` from `cancion` order by `id` desc limit 20";
			PreparedStatement pst = con.prepareStatement(sqlQuery);
			
			//obtener resultados
			ResultSet rs = pst.executeQuery();
			
			while( rs.next()) {
				c.setNombre(rs.getString("nombre"));
				c.setArtista(rs.getString("artista"));
				c.setDuracion(rs.getString("duracion"));
				c.setCover(rs.getString("cover"));
				canciones.add(c);
			}
		*/	
	
		}catch (Exception e) {
			msg = "Hubo algun error";
			e.printStackTrace();
		}finally {
			request.setAttribute("msg", msg);
			request.setAttribute("dao", dao);//funciona, se pasa
			request.setAttribute("canciones", canciones);
			request.getRequestDispatcher("listarCancion.jsp").forward(request, response);
		}
		
		
	}
}
