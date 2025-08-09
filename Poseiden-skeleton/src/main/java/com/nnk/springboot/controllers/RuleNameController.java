package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RuleNameController {

    private final RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    // LISTE
    @GetMapping("/ruleName/list")
    public String listAllRules(Model model, Principal principal) {
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        return "ruleName/list";
    }

    // FORM AJOUT
    @GetMapping("/ruleName/add")
    public String showAddForm(RuleName ruleName, Model model, Principal principal) {
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        return "ruleName/add";
    }

    // TRAITEMENT AJOUT
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model, Principal principal) {
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.saveRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    // FORM UPDATE
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, Principal principal) {
        RuleName ruleName = ruleNameService.getRuleNameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        if (principal != null) model.addAttribute("remoteUser", principal.getName());
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRule(@PathVariable("id") Integer id,
                             @Valid RuleName ruleName,
                             BindingResult result,
                             Model model,
                             Principal principal) {

        if (principal != null) model.addAttribute("remoteUser", principal.getName());

        if (result.hasErrors()) {
            ruleName.setId(id);
            return "ruleName/update";
        }

        ruleNameService.update(id, ruleName);
        return "redirect:/ruleName/list";
    }


    // SUPPRESSION
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRule(@PathVariable("id") Integer id) {
        ruleNameService.deleteRuleName(id);
        return "redirect:/ruleName/list";
    }
}
