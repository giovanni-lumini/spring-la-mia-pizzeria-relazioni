package org.exercise.spring.spring_pizzeria.controllers;

import java.util.List;

import org.exercise.spring.spring_pizzeria.model.Pizza;
import org.exercise.spring.spring_pizzeria.repository.PizzeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizze")
public class PizzeController {

    @Autowired
    private PizzeRepository pizzeRepository;

    // INDEX
    @GetMapping
    public String index(Model model) {
        List<Pizza> pizze = pizzeRepository.findAll();
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzeRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizze/show";
    }

    // SEARCH BY NAME
    @GetMapping("/nomePizza")
    // http://localhost:8080/pizze/nomePizza?nome=margherita
    public String nomePizza(@RequestParam(name = "nome") String nome, Model model) {
        List<Pizza> pizze = pizzeRepository.findByNome(nome);
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizze, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizze/create";
        }
        pizzeRepository.save(formPizze);
        return "redirect:/pizze";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzeRepository.findById(id).get());
        return "pizze/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("pizza") Pizza formPizze, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizze/edit";
        }
        pizzeRepository.save(formPizze);
        return "redirect:/pizze";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        pizzeRepository.deleteById(id);
        return "redirect:/pizze";

    }

}
