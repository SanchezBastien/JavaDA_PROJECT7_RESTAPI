package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    // LISTE
    @GetMapping("/trade/list")
    public String listAllTrades(Model model, Principal principal) {
        model.addAttribute("trades", tradeService.getAllTrades());
        if (principal != null) {
            model.addAttribute("remoteUser", principal.getName());
        }
        return "trade/list";
    }

    // FORMULAIRE AJOUT
    @GetMapping("/trade/add")
    public String showAddForm(Trade trade, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("remoteUser", principal.getName());
        }
        return "trade/add";
    }

    // TRAITEMENT AJOUT
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("remoteUser", principal.getName());
        }
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.saveTrade(trade);
        return "redirect:/trade/list";
    }

    // FORMULAIRE MISE À JOUR
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, Principal principal) {
        Trade trade = tradeService.getTradeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        if (principal != null) {
            model.addAttribute("remoteUser", principal.getName());
        }
        return "trade/update";
    }

    // TRAITEMENT MISE À JOUR
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("remoteUser", principal.getName());
        }
        if (result.hasErrors()) {
            trade.setId(id);
            return "trade/update";
        }
        tradeService.saveTrade(trade);
        return "redirect:/trade/list";
    }

    // SUPPRESSION
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        tradeService.deleteTrade(id);
        return "redirect:/trade/list";
    }
}
