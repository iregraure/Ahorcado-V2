package com.ahorcado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahorcado.entity.Partida;
import com.ahorcado.service.PartidaService;

@RestController
@RequestMapping(path = "/ahorcado")
public class PartidaController
{
	// Servicios
	@Autowired
	private PartidaService partidaService;

	// GET
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getPartida(@PathVariable int id)
	{
		ResponseEntity<?> response;
		try
		{
			Partida partida = partidaService.getPartida(id);
			response = ResponseEntity.ok(partida);
		}
		catch (Exception e)
		{
			response = ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}
	
	// POST
	@PostMapping
	public ResponseEntity<?> addPartida()
	{
		ResponseEntity<?> response;
		Partida partida = partidaService.creaPartida();

		response = ResponseEntity.ok(partida);
		return response;
	}

	// PUT
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> guessLetter(@PathVariable int id, @RequestParam String letter)
	{
		ResponseEntity<?> response;
		Partida partida = null;
		try
		{
			if (letter.length() == 1)
			{
				partida = partidaService.compruebaLetra(id, letter);
			}
			else
			{
				partida = partidaService.compruebaPalabra(id, letter);
			}
			response = ResponseEntity.ok(partida);
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("Partida no existe")) 
			{
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(partida);				
			}
			else
			{
				partida = (Partida) getPartida(id).getBody();
				partida.setMensaje(e.getMessage());
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(partida);
			}
		}
		
		return response;
	}

}
