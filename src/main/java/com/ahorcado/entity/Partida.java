package com.ahorcado.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Partida
{

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String palabra;
	private String palabraOculta;
	private int errores;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="letters", joinColumns = @JoinColumn(referencedColumnName = "id"))
	private Set<Character> letras;
	private boolean terminado;
	private String mensaje;

	// Constructor
	public Partida()
	{
		this.errores = 0;
		this.letras = new HashSet<Character>();
		this.terminado = false;
		this.mensaje = "";
	}
	
	// Métodos get y set
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getPalabra()
	{
		return palabra;
	}

	public void setPalabra(String palabra)
	{
		this.palabra = palabra;
	}

	public String getPalabraOculta()
	{
		return palabraOculta;
	}

	public void setPalabraOculta(String palabraOculta)
	{
		this.palabraOculta = palabraOculta;
	}

	public int getErrores()
	{
		return errores;
	}

	public void setErrores(int errores)
	{
		this.errores = errores;
	}

	public Set<Character> getLetras()
	{
		return letras;
	}

	public void setLetras(Set<Character> letras)
	{
		this.letras = letras;
	}

	public boolean isTerminado()
	{
		return terminado;
	}

	public void setTerminado(boolean terminado)
	{
		this.terminado = terminado;
	}

	public String getMensaje()
	{
		return mensaje;
	}

	public void setMensaje(String mensaje)
	{
		this.mensaje = mensaje;
	}

}
