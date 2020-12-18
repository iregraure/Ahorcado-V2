package com.ahorcado.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import com.ahorcado.entity.Partida;
import com.ahorcado.repo.PartidaRepository;

import javassist.NotFoundException;

public class PartidaServiceTest
{
	// Subject Under Test
	private PartidaService sut;
	
	// Dependencias
	private Partida mockedPartida;
	private PartidaRepository mockedRepo;
	
	@BeforeEach
	private void init()
	{
		sut = new PartidaService();
		mockedPartida = mock(Partida.class);
		mockedRepo = mock(PartidaRepository.class);
		sut.setPartidaRepository(mockedRepo);
	}
	
	@Test
	public void getPartidaThrowsException()
	{
		try
		{
			Mockito.when(mockedRepo.findPartidaById(Mockito.anyInt())).thenReturn(null);
			sut.getPartida(2);
		}
		catch(NotFoundException nfe)
		{
			Assert.isInstanceOf(NotFoundException.class, nfe);
		}
		catch(Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}
	
	@Test
	public void getPartida()
	{
		try
		{
			Mockito.when(mockedRepo.findPartidaById(Mockito.anyInt())).thenReturn(mockedPartida);
			assert(mockedPartida == sut.getPartida(2));
		}
		catch(Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}
	
	@Test
	public void creaPartida() 
	{
		try
		{
			sut = mock(PartidaService.class);
			Mockito.when(sut.creaPartida()).thenReturn(mockedPartida);
			assert(sut.creaPartida() == mockedPartida);
		}
		catch(Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}
	
	@Test
	public void generaPalabra()
	{
		try
		{
			sut = mock(PartidaService.class);
			Mockito.when(sut.generaPalabra()).thenReturn("ahorcado");
			assert(sut.generaPalabra().equals("ahorcado"));
		}
		catch(Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}

	// Falla
	@Test
	public void ocultaPalabra()
	{
		try
		{
			String oculta = "********";
			Mockito.when(sut.ocultaPalabra("ahorcado")).thenReturn(oculta);
			assert(sut.ocultaPalabra("ahorcado").equals(oculta));
		}
		catch(Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}
	
	@Test
	public void letraRepetidaReturnsTrue()
	{
		try
		{
			Mockito.when(sut.letraRepetida(Mockito.any(), Mockito.anyChar())).thenReturn(true);
			assert(sut.letraRepetida(mockedPartida, 'a'));
		}
		catch(Exception e)
		{
			throw new AssertionError(e.getMessage());
		}
	}
	
//	protected boolean letraRepetida(Partida partida, char letra)
//	{
//		boolean resul = false;
//		Set<Character> letras = partida.getLetras();
//		if (letras.contains(letras))
//		{
//			resul = true;
//		}
//		return resul;
//	}
	
//	protected String sustituyeLetra(Partida partida, char letra)
//	{
//		String palabra = partida.getPalabra();
//		String oculta = partida.getPalabraOculta();
//		StringBuilder aux = new StringBuilder();
//		// Recorremos la palabra
//		for (int i = 0; i < palabra.length(); i++)
//		{
//			// Si la letra es igual se añade a aux
//			if (palabra.charAt(i) == letra)
//			{
//				aux.append(letra);
//			}
//			// Si no, se añade lo que hubiese acertado
//			else
//			{
//				aux.append(oculta.charAt(i));
//			}
//		}
//		return aux.toString();
//	}
	
//	public Partida compruebaLetra(int id, String letraEnviada) throws Exception
//	{
//		Partida partida = partidaRepository.findPartidaById(id);
//		if (partida == null)
//		{
//			throw new NotFoundException("Partida no existe");
//		}
//		// Comprobamos que la partida no haya terminado
//		if (partida.isTerminado())
//		{
//			throw new IllegalAccessException("Partida terminada");
//		}
//		// Obtenemos la palabra de la partida y convertimos el String a char
//		String palabra = partida.getPalabra();
//		char letra = letraEnviada.charAt(0);
//		// Comprobar si la letra ya se ha dicho
//		boolean letraRepetida = letraRepetida(partida, letra);
//		if (letraRepetida)
//		{
//			throw new IllegalArgumentException("Letra repetida");
//		}
//		partida.setMensaje("");
//		// Añadir la letra a la lista de letras
//		partida.getLetras().add(letra);
//		// Comprobamos si la letra existe dentro de la palabra
//		if (palabra.indexOf(letra) != -1)
//		{
//			// Sustituimos la letra
//			partida.setPalabraOculta(sustituyeLetra(partida, letra));
//			if (partida.getPalabra().equalsIgnoreCase(partida.getPalabraOculta()))
//			{
//				partida.setTerminado(true);
//				partida.setMensaje("Partida terminada, has ganado");
//			}
//		}
//		else
//		{
//			int numErrores = partida.getErrores();
//			numErrores++;
//			partida.setErrores(numErrores);
//			if (numErrores == MAX_ERRORES)
//			{
//				partida.setTerminado(true);
//				partida.setMensaje("Partida terminada, has perdido");
//			}
//		}
//		partidaRepository.save(partida);
//		return partida;
//	}
	
//	public Partida compruebaPalabra(int id, String palabraEnviada) throws Exception
//	{
//		Partida partida = partidaRepository.findPartidaById(id);
//		if (partida == null)
//		{
//			throw new NotFoundException("Partida no existe");
//		}
//		// Comprobamos que la partida no haya terminado
//		if (partida.isTerminado())
//		{
//			throw new IllegalAccessException("Partida terminada");
//		}
//		if (partida.getPalabra().equalsIgnoreCase(palabraEnviada))
//		{
//			partida.setTerminado(true);
//			partida.setMensaje("Partida terminada, has ganado");
//		}
//		else
//		{
//			int numErrores = partida.getErrores();
//			numErrores++;
//			partida.setErrores(numErrores);
//			if (numErrores == MAX_ERRORES)
//			{
//				partida.setTerminado(true);
//				partida.setMensaje("Partida terminada, has perdido");
//			}
//		}
//		return partida;
//	}
}