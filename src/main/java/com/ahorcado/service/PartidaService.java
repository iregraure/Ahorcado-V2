package com.ahorcado.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahorcado.entity.Partida;
import com.ahorcado.repo.PartidaRepository;

import javassist.NotFoundException;

@Service
public class PartidaService
{
	// Repositorio
	@Autowired
	private PartidaRepository partidaRepository;

	private final int MAX_ERRORES = 7;

	// Método que devuelve una partida dado su id
	public Partida getPartida(int id) throws NotFoundException
	{
		Partida partida = partidaRepository.findPartidaById(id);
		if(partida == null)
		{
			throw new NotFoundException("La partida no existe");
		}
		return partida;
	}
	
	// Método para crear una partida
	public Partida creaPartida()
	{
		// Creamos una partida nueva
		Partida partida = new Partida();
		// Obtenemos una palabra aleatoria y su forma oculta
		String palabra = generaPalabra();
		String oculta = ocultaPalabra(palabra);
		// Le añadimos la palabra y su forma oculta a la partida
		partida.setPalabra(palabra);
		partida.setPalabraOculta(oculta);
		partidaRepository.save(partida);
		return partida;
	}

	// Método para generar una palabra aleatoria
	protected String generaPalabra()
	{
		// Creamos el array con las posibles frases
		List<String> frases = Arrays.asList("vamos a aprobar todos", "voy a ser mas positivo", "odio esta asignatura",
				"facil divertido y entretenido", "esto es muy facil", "bienvenidos al jaracanda", "javi se nos ha ido");
		// Calculamos un número aleatorio entre 0 y la longitud del array
		int aleatorio = (int) Math.floor(Math.random() * (frases.size()));
		// Guardamos en una variable lo que haya en la posición calculada
		String palabra = frases.get(aleatorio);
		return palabra;
	}

	// Método para mostrar una palabra solo usando *
	protected String ocultaPalabra(String palabra)
	{
		StringBuilder oculta = new StringBuilder();
		// Recorremos la palabra
		for (int i = 0; i < palabra.length(); i++)
		{
			// Guardamos el caracter en la posición i
			char c = palabra.charAt(i);
			// Si es un espacio lo añadimos, y si no añadimos *
			if (c == ' ')
			{
				oculta.append(' ');
			}
			else
			{
				oculta.append('*');
			}
		}
		return oculta.toString();
	}

	// Método que comprueba si una letra está en la palabra
	public Partida compruebaLetra(int id, String letraEnviada) throws Exception
	{
		Partida partida = partidaRepository.findPartidaById(id);
		if (partida == null)
		{
			throw new NotFoundException("Partida no existe");
		}
		// Comprobamos que la partida no haya terminado
		if (partida.isTerminado())
		{
			throw new IllegalAccessException("Partida terminada");
		}
		// Obtenemos la palabra de la partida y convertimos el String a char
		String palabra = partida.getPalabra();
		char letra = letraEnviada.charAt(0);
		// Comprobar si la letra ya se ha dicho
		boolean letraRepetida = letraRepetida(partida, letra);
		if (letraRepetida)
		{
			throw new IllegalArgumentException("Letra repetida");
		}
		partida.setMensaje("");
		// Añadir la letra a la lista de letras
		partida.getLetras().add(letra);
		// Comprobamos si la letra existe dentro de la palabra
		if (palabra.indexOf(letra) != -1)
		{
			// Sustituimos la letra
			partida.setPalabraOculta(sustituyeLetra(partida, letra));
			if (partida.getPalabra().equalsIgnoreCase(partida.getPalabraOculta()))
			{
				partida.setTerminado(true);
				partida.setMensaje("Partida terminada, has ganado");
			}
		}
		else
		{
			int numErrores = partida.getErrores();
			numErrores++;
			partida.setErrores(numErrores);
			if (numErrores == MAX_ERRORES)
			{
				partida.setTerminado(true);
				partida.setMensaje("Partida terminada, has perdido");
			}
		}
		partidaRepository.save(partida);
		return partida;
	}

	// Método que comprueba si la letra ya se ha dicho
	protected boolean letraRepetida(Partida partida, char letra)
	{
		boolean resul = false;
		Set<Character> letras = partida.getLetras();
		if (letras.contains(letras))
		{
			resul = true;
		}
		return resul;
	}

	// Método que sustituye los * por la letra indicada en la palabra oculta
	protected String sustituyeLetra(Partida partida, char letra)
	{
		String palabra = partida.getPalabra();
		String oculta = partida.getPalabraOculta();
		StringBuilder aux = new StringBuilder();
		// Recorremos la palabra
		for (int i = 0; i < palabra.length(); i++)
		{
			// Si la letra es igual se añade a aux
			if (palabra.charAt(i) == letra)
			{
				aux.append(letra);
			}
			// Si no, se añade lo que hubiese acertado
			else
			{
				aux.append(oculta.charAt(i));
			}
		}
		return aux.toString();
	}

	// Método que comprueba si la palabra es correcta
	public Partida compruebaPalabra(int id, String palabraEnviada) throws Exception
	{
		Partida partida = partidaRepository.findPartidaById(id);
		if (partida == null)
		{
			throw new NotFoundException("Partida no existe");
		}
		// Comprobamos que la partida no haya terminado
		if (partida.isTerminado())
		{
			throw new IllegalAccessException("Partida terminada");
		}
		if (partida.getPalabra().equalsIgnoreCase(palabraEnviada))
		{
			partida.setTerminado(true);
			partida.setMensaje("Partida terminada, has ganado");
		}
		else
		{
			int numErrores = partida.getErrores();
			numErrores++;
			partida.setErrores(numErrores);
			if (numErrores == MAX_ERRORES)
			{
				partida.setTerminado(true);
				partida.setMensaje("Partida terminada, has perdido");
			}
		}
		return partida;
	}

	public void setPartidaRepository(PartidaRepository partidaRepository)
	{
		this.partidaRepository = partidaRepository;
	}
	
}
