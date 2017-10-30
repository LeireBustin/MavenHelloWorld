package com.acme.maven.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.acme.maven.modelo.pojo.Cancion;

public class TestCancionDAO {
	
	static CancionDAO dao;
	//Objetos DE PRUEBA:
	static Cancion cancionMock;
	static final String NOMBRE = "Soldadito marinero";
	static final String ARTISTA = "Fito y los Fiti";
	static final String DURACION = "3:59";
	static final String COVER = "http://1.bp.blogspot.com/-ECbO0u4Leyc/VUPO-S7WZiI/AAAAAAAAF3k/gkGx8g3nN5U/s1600/soldadito-marinero-fito-fitipaldis.jpg";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		dao = CancionDAO.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		
		// Crear Pojo
		cancionMock = new Cancion();
		cancionMock.setNombre(NOMBRE);
		cancionMock.setArtista(ARTISTA);
		cancionMock.setDuracion(DURACION);
		cancionMock.setCover(COVER);
		
		
		// Insertar Pojo en la BBDD
		assertTrue(dao.create(cancionMock));
	}

	@After
	public void tearDown() throws Exception {
		
		// eliminar de la BBDD
		assertTrue(dao.delete(cancionMock.getId()));
		cancionMock = null;
	}

	@Test
	public void testDelete() {
		assertFalse("No se puede eliminar un id que no existe", dao.delete(0));
	}
	
	@Test
	public void testCreate() {
		assertFalse("No se puede crear null", dao.create(null));
		
		cancionMock.setNombre("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertFalse("No se puede crear un nombe tan largo: campo nombre > VARCHAR(150)", dao.create(cancionMock));
	}
	
	@Test
	public void testUpdate() {
		
		
		String nombreUpdate = "La maca";
		String artistaUpdate = "La gggggggg";
		String duracionUpdate = "0:01";
		String coverUpdate = "La maca.jpg";
		
		
		int id = cancionMock.getId();
		cancionMock.setNombre(nombreUpdate);
		cancionMock.setArtista(artistaUpdate);
		cancionMock.setDuracion(duracionUpdate);
		cancionMock.setCover(coverUpdate);
		
		// creamos
		assertTrue("No modifica", dao.update(cancionMock, id));
		
		assertEquals(id, cancionMock.getId());
		assertEquals(nombreUpdate, cancionMock.getNombre());
		assertEquals(artistaUpdate, cancionMock.getArtista());
		assertEquals(duracionUpdate, cancionMock.getDuracion());
		assertEquals(coverUpdate, cancionMock.getCover());
		
		// comprobar que funcione cuando no deberia funcionar
		assertFalse("No se puede modificar lo que no existe", dao.update(cancionMock, 0));
		assertFalse("No se puede modificar/meter un null", dao.update(null, id));//TODO ?
		
	}
	
	@Test
	public void testFindAll() {
		
		ArrayList<Cancion> canciones = (ArrayList<Cancion>) dao.findAll();
		assertNotNull(canciones);
		
		int contadorCanciones = canciones.size();
		assertTrue("Las canciones >=0 y <= 100 (LIMIT) ", contadorCanciones >= 0 && contadorCanciones <= Persistable.LIMIT );
		
		//insertamos 2 canciones
		dao.create(cancionMock);
		dao.create(cancionMock);
		
		ArrayList<Cancion> canciones2 = (ArrayList<Cancion>) dao.findAll();
		assertEquals(contadorCanciones + 2, canciones2.size());
		
		assertTrue("No ordena descendentemente por Id", canciones2.get(0).getId() > canciones2.get(1).getId());
		
		
	}
	
	@Test
	public void testFindById() {

		assertNull("No se puede buscar lo que no existe", dao.findById(0));
		
		Cancion cBuscada = dao.findById(cancionMock.getId());
		assertNotNull(cBuscada);
		//assertEquals(id, cancionMock.getId());
		
		assertEquals(cBuscada.getId(), cancionMock.getId());
		assertEquals(cBuscada.getNombre(), cancionMock.getNombre());
		assertEquals(cBuscada.getArtista(), cancionMock.getArtista());
		assertEquals(cBuscada.getCover(), cancionMock.getCover());
		assertEquals(cBuscada.getDuracion(), cancionMock.getDuracion());
	}
	
	
	
}
