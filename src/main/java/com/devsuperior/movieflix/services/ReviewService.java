package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		var review = getAttributesFromDto(dto);	
		review = repository.save(review);
		return new ReviewDTO(review);
	}

	@Transactional(readOnly = true)
	private Review getAttributesFromDto(ReviewDTO dto) {
		Review review = new Review();
		review.setText(dto.getText());
		
		var user = userRepository.getOne(userService.getUserInfo().getId());
		var movie = movieRepository.getOne(dto.getMovieId());
		
		review.setUser(user);
		review.setMovie(movie);

		return review;
	}

}
