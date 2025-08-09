package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Optional<Rating> getRatingById(Integer id) {
        return ratingRepository.findById(id);
    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}
