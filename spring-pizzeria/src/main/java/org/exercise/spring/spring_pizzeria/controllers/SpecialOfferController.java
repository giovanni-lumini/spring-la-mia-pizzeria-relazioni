package org.exercise.spring.spring_pizzeria.controllers;

import org.exercise.spring.spring_pizzeria.model.SpecialOffer;
import org.exercise.spring.spring_pizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/specialoffer")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    // CREATE
    @PostMapping("/create")
    public String store(@ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "specialOffer/create-or-edit";
        }
        specialOfferRepository.save(formSpecialOffer);

        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();
    }
}
