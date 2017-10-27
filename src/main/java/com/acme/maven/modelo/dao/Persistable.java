package com.acme.maven.modelo.dao;

import java.util.List;


public interface Persistable<P> {
	//clase generica: letra en MAYUS
	
	final static int LIMIT = 100; //en una Inerface ya es PUBLIC FINAL STATIC
	
	/**Busca los ultimos registros ordenados por id descendente y limitados por <code>LIMIT</code>
	 * Nos va a mostrar todos los registros  registros
	 * @return List de Pokjos
	 */
	List<P> findAll();
	
	/**
	 * Busca un registro x su id
	 * @param id
	 * 				int identificador
	 * @return Pojo si lo enccuentra, null en caso contrario
	 */
	P findById();
	
	/**
	 * Inserta un nuevo registro por su identificador
	 * @param pojo
	 * @return
	 */
	boolean create(P pojo);
	
	/**
	 * Modifica un Pojo pasandole su identificador
	 * @param pojo
	 * 				Objeto a modificar
	 * @param id
	 * 				identificador del registro en la bbdd, si es < 1 retorna false
	 * @return true si insertado correctamente, false en caso contrario o si id < 1
	 */
	boolean update(P pojo, int id);
	
	//TODO REVISAR
	/**
	 * Eliminar un Pojo pasandole su identificador
	 * @param id
	 * 				identificador del registro en la bbdd, si es < 1 retorna false
	 * @return true si insertado correctamente, false en caso contrario o si id < 1
	 */
	boolean delete(int id);
	
	
	
}
