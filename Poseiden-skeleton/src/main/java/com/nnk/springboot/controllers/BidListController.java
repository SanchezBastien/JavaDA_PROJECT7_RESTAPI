package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class BidListController {

    private final BidListService service;

    public BidListController(BidListService service) {
        this.service = service;
    }

    @GetMapping("/bidList/list")
    public String list(Model model, Principal principal) {
        model.addAttribute("bidLists", service.getAll());
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String showAddForm(BidList bidList, Model model, Principal principal) {
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model, Principal principal) {
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        if (result.hasErrors()) return "bidList/add";
        service.create(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model, Principal principal) {
        BidList bidList = service.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id: " + id));
        model.addAttribute("bidList", bidList);
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String update(@PathVariable Integer id,
                         @Valid BidList bidList,
                         BindingResult result,
                         Model model,
                         Principal principal) {
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        if (result.hasErrors()) {
            bidList.setId(id); // garde l'id quand on ré-affiche le formulaire
            return "bidList/update";
        }
        service.update(id, bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/bidList/list";
    }
}
