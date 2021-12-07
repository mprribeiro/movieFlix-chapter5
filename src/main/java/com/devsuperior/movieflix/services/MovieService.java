package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		var movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found for id " + id));
		return new MovieDTO(movie);
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
		var movies = repository.findByGenreId(genreId, pageable);
		return movies.map(movie -> new MovieDTO(movie));
	}

}
