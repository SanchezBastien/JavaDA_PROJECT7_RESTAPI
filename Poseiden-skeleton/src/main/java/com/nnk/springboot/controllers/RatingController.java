package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // LISTE
    @GetMapping("/rating/list")
    public String listAllRatings(Model model, Principal principal) {
        model.addAttribute("ratings", ratingService.getAllRatings());
        if (principal != null) {
            model.addAttribute("remoteUser", principal.getName());
        }
        return "rating/list";
    }

    // FORMULAIRE AJOUT
    @GetMapping("/rating/add")
    public String showAddForm(Rating rating) {
        return "rating/add";
    }

    // TRAITEMENT AJOUT
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/add";
        }
        ratingService.saveRating(rating);
        return "redirect:/rating/list";
    }

    // FORMULAIRE MISE À JOUR
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    // TRAITEMENT MISE À JOUR
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            rating.setId(id);
            return "rating/update";
        }
        ratingService.saveRating(rating);
        return "redirect:/rating/list";
    }

    // SUPPRESSION
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}
