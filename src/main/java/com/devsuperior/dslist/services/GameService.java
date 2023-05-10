package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service // ou @Component = idem
public class GameService {
	
	// injetando uma instância de GameRepository no GameService
	// service injeta um repository
	@Autowired
	private GameRepository gameRepository;

	// assegurando que não fara operacao de escrita
	@Transactional(readOnly = true) 
	public GameDTO findById(Long Id) {
		Game result = gameRepository.findById(Id).get();
		return new GameDTO(result);
	}

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game> result = gameRepository.findAll();
		return result.stream().map(x-> new GameMinDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId){
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		return result.stream().map(x-> new GameMinDTO(x)).toList();
	}
}
