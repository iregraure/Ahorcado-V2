package com.ahorcado.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahorcado.entity.Partida;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Integer>
{

	// Get de una partida por id
	public Partida findPartidaById(int id);
	
}
