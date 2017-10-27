package com.acme.maven.modelo.pojo;

public class Cancion {

	private int id;
	private String nombre;
	private String artista;
	private String duracion;
	private String cover;
	
	public Cancion() {
		super();
		this.id = -1;
		this.nombre = "";
		this.artista = "";
		this.duracion = "";
		this.cover = "";
	}

	



	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion)  {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", nombre=" + nombre + ", artista=" + artista + ", duracion=" + duracion
				+ ", cover=" + cover + "]";
	}

	
}