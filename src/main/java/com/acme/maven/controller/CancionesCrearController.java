package com.acme.maven.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrearController
 */
@WebServlet("/canciones/crear")
public class CancionesCrearController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
				////Cancion de prueba
				String nombre =	request.getAttribute("nombre");
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
		*/
	}
	
}
