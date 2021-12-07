package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		var movieDTO = service.findById(id);
		return ResponseEntity.ok(movieDTO);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findByGenre(@RequestParam(name = "genreId", required = false) Long genreId,
			                                          @PageableDefault(sort = "title") Pageable pageable) {
		var moviesDTO = service.findByGenre(genreId, pageable);
		return ResponseEntity.ok(moviesDTO);
	}
}
