package fr.oz.boursevelo.controller;

import fr.oz.boursevelo.model.Personne;
import fr.oz.boursevelo.service.PersonneService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BourseController {
    private final PersonneService personneService;

@GetMapping("/reception")
public String reception(){
    return "reception";

}
@GetMapping("/restitution")
public String restitution(){
    return "restitution";

}

@GetMapping("/rechercheFicheReception")
public String rechercheFicheReception(@NotNull Model model){
    Iterable<Personne> listPersonne = personneService.getPersonnes();
    model.addAttribute("personnes", listPersonne);
    return "rechercheFicheReception";

}

    @GetMapping("/deletePersonne/{id}")
    public ModelAndView deletePersonne(@PathVariable("id") final Long id) {
        personneService.deletePersonne(id);
        return new ModelAndView("redirect:/rechercheFicheReception");
    }

    @GetMapping("/creationFiche")
    public String creationFiche(Model model){
        Personne pers = new Personne();
        model.addAttribute("personne", pers);
        return "creationFiche";

    }

    @PostMapping("/savePersonne")
    public ModelAndView savePersonne(@ModelAttribute Personne personne) {
        personneService.savePersonne(personne);
        return new ModelAndView("redirect:/rechercheFicheReception");
    }

    @PostMapping("/savePersonneValider")
    public ModelAndView savePersonneValider(@ModelAttribute Personne personne) {
        Personne createdPersonne = personneService.savePersonne(personne);
        return new ModelAndView("redirect:/selectionnerPersonne/"+ createdPersonne.getId());
    }

    @GetMapping("/updatePersonne/{id}")
    public String updatePersonne(@PathVariable("id") final int id, Model model) {
        Personne pers = personneService.getPersonne(id);
        model.addAttribute("personne", pers);
        return "updatePersonne";
    }

    @GetMapping("/selectionnerPersonne/{id}")
    public String selectionnerPersonne(@PathVariable("id") final int id, Model model){
        Personne pers = personneService.getPersonne(id);
        model.addAttribute("personne", pers);
        return "validerPersonne";
    }

}
