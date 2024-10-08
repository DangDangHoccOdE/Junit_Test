package com.animalreview.api.controller;

import com.animalreview.api.servies.ReviewService;
import com.animalreview.api.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/users/{userId}/animal/{animalId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "userId") int userId,@PathVariable(value = "animalId") int animalId, @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(userId,animalId,reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/animal/{animalId}/reviews")
    public List<ReviewDto> getReviewsByAnimalId(@PathVariable(value = "animalId") int animalId){
        return reviewService.getReviewByAnimalId(animalId);
    }

    @GetMapping("/animal/{animalId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "animalId") int animalId,@PathVariable(value = "id") int reviewId){
        ReviewDto reviewDto = reviewService.getReviewById(animalId,reviewId);
        return new ResponseEntity<>(reviewDto,HttpStatus.OK);
    }


    @PutMapping("/users/{userId}/animal/{animalId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable(value = "userId") int userId,
            @PathVariable(value = "animalId") int animalId,
            @PathVariable(value="id") int reviewId,
            @RequestBody ReviewDto reviewDto
    ){
        ReviewDto updateReview = reviewService.updateReview(userId,animalId,reviewId,reviewDto);
        return new ResponseEntity<>(updateReview,HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/animal/{animalId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(
            @PathVariable(value = "userId") int userId,
            @PathVariable(value = "animalId") int animalId,
            @PathVariable(value = "id") int reviewId
    ){
        reviewService.deleteReview(userId,animalId,reviewId);
        return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
    }
}
