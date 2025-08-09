package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class CurvePointController {

    private final CurvePointService curvePointService;

    public CurvePointController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    // LISTE
    @GetMapping("/curvePoint/list")
    public String listAllCurvePoints(Model model, Principal principal) {
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoints());
        if (principal != null) {
            model.addAttribute("remoteUser", principal.getName());
        }
        return "curvePoint/list";
    }

    // AJOUT (formulaire)
    @GetMapping("/curvePoint/add")
    public String showAddForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    // AJOUT (validation)
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.saveCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    // MISE À JOUR (formulaire)
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    // MISE À JOUR (validation)
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            curvePoint.setId(id);
            return "curvePoint/update";
        }
        curvePointService.saveCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    // SUPPRESSION
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
