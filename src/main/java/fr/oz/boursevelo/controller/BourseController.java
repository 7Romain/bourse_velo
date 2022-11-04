package fr.oz.boursevelo.controller;

import fr.oz.boursevelo.model.Article;
import fr.oz.boursevelo.model.Personne;
import fr.oz.boursevelo.service.ArticleService;
import fr.oz.boursevelo.service.PersonneService;
import fr.oz.boursevelo.model.TaxiTable;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BourseController {


    private final PersonneService personneService;
    private final ArticleService articleService;

@GetMapping("/reception/{table}")
public String reception(@PathVariable("table") final int table, Model model){
    TaxiTable taxiTable = new TaxiTable();
    taxiTable.setTaxi(table);
    model.addAttribute("taxiTable" , taxiTable );
    return "reception";

}
@GetMapping("/restitution/{table}")
public String restitution(@PathVariable("table") final int table, Model model){
    TaxiTable taxiTable = new TaxiTable();
    taxiTable.setTaxi(table);
    model.addAttribute("taxiTable" , taxiTable );
    return "restitution";

}

@GetMapping("/rechercheFicheReception/{table}")
public  ModelAndView rechercheFicheReception(@PathVariable("table") final int table, @NotNull Model model){


    System.out.println(model.getAttribute("personnes"));

    if(model.getAttribute("personnes")==null){
        Iterable<Personne> listPersonne = personneService.getPersonnes();
        model.addAttribute("personnes", listPersonne);
    }

    TaxiTable taxiTable = new TaxiTable();
    taxiTable.setTaxi(table);
    model.addAttribute("taxiTable" , taxiTable );

    return new ModelAndView("rechercheFicheReception" );

}

    @GetMapping("/deletePersonne/{id}/{table}")
    public ModelAndView deletePersonne(@PathVariable("id") final Long id,  @PathVariable("table") final int table, Model model) {
        personneService.deletePersonne(id);
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        String tab = String.valueOf(taxiTable.getTaxi());
        return new ModelAndView("redirect:/rechercheFicheReception/" + tab);
    }

    @GetMapping("/creationFiche/{table}")
    public String creationFiche(@PathVariable("table") final int table, Model model){
        Personne pers = new Personne();
        model.addAttribute("personne", pers);
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        return "creationFiche";

    }



    @PostMapping("/savePersonne/{table}")
    public ModelAndView savePersonne( @PathVariable("table") final int table, @ModelAttribute Personne personne, Model model) {
        personneService.savePersonne(personne);
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        String tab = String.valueOf(taxiTable.getTaxi());
        return new ModelAndView("redirect:/rechercheFicheReception/" + tab);
    }

    @PostMapping("/savePersonneValider/{table}")
    public ModelAndView savePersonneValider(@PathVariable("table") final int table, @ModelAttribute Personne personne, Model model) {
        Personne createdPersonne = personneService.savePersonne(personne);
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        String tab = String.valueOf(taxiTable.getTaxi());
        return new ModelAndView("redirect:/selectionnerPersonne/"+ createdPersonne.getId() +"/"+tab);
    }

    @GetMapping("/updatePersonne/{id}/{table}")
    public String updatePersonne(@PathVariable("id") final int id, @PathVariable("table") final int table, Model model) {
        Personne pers = personneService.getPersonne(id);
        model.addAttribute("personne", pers);
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        return "updatePersonne" ;
    }

    @GetMapping("/selectionnerPersonne/{id}/{table}")
    public String selectionnerPersonne(@PathVariable("id") final int id, @PathVariable("table") final int table, Model model){
        Personne pers = personneService.getPersonne(id);
        model.addAttribute("personne", pers);
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        return "validerPersonne";
    }

    @GetMapping("/ajouterArticle/{id}/{table}")
    public ModelAndView ajouterArticle(@PathVariable("id") final int id , @PathVariable("table") final int table , Model model) {
        Personne pers = personneService.getPersonne(id);
        model.addAttribute("personne", pers);
        Iterable<Article> listArticles = articleService.getArticles(id);
        model.addAttribute("articles", listArticles);
        Article article = new Article();
        article.setIdVendeur(id);
        article.setTableEnregistrement(table);
        model.addAttribute("article", article);
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        return new ModelAndView ("ajouterArticle","personne", pers );
}

    @PostMapping("/saveArticle/{table}")
    public ModelAndView saveArticle(@ModelAttribute Article article, Model model, @PathVariable("table") final int table) {
        TaxiTable taxiTable = new TaxiTable();
        article.setTableEnregistrement(table);
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable" , taxiTable );
        article.setTableEnregistrement(table);

        articleService.saveArticle(article);

        String tab = String.valueOf(taxiTable.getTaxi());
        Personne pers = personneService.getPersonne((int) article.getIdVendeur());
        model.addAttribute("personne", pers);
        return new ModelAndView("redirect:/ajouterArticle/" + article.getIdVendeur() + "/" + tab);
    }

    @GetMapping("/")
        public String acceuil(Model model){
        TaxiTable taxiTable = new TaxiTable();
            model.addAttribute("taxiTable", taxiTable);
            return "choixTable";
    }

    @PostMapping("/choisirTable")
    public ModelAndView choisirTable(@ModelAttribute TaxiTable taxiTable) {


      int tab = taxiTable.getTaxi();

        return new ModelAndView("redirect:/debut/" +tab ,"taxiTable" ,taxiTable );
    }

    @GetMapping("/debut/{table}")
    public String afficherDebut(@PathVariable("table") final int table, Model model){
    TaxiTable taxiTable = new TaxiTable();
    taxiTable.setTaxi(table);
    model.addAttribute("taxiTable" , taxiTable );



        return "debut";

    }

    @PostMapping("/chercherPersonne/{table}")
    public ModelAndView afficherTableauFiltre(
            @PathVariable("table") final int table , Model model,
            @RequestParam(value = "zoneSearch", required = true) String zoneSearch
    ){
    TaxiTable taxiTable = new TaxiTable();
    taxiTable.setTaxi(table);
    model.addAttribute("taxiTable", taxiTable);

        Iterable<Personne> chercherPersonne = personneService.chercherPersonne(zoneSearch);
        model.addAttribute("filtre", zoneSearch);
        model.addAttribute("personnes", chercherPersonne);
        System.out.println(model.getAttribute("personnes"));


        return new ModelAndView("rechercheFicheReception", "model" , model) ;

    }
    @GetMapping("/chercherPersonne/{table}")
    public ModelAndView afficherTableauFiltre2(
            @PathVariable("table") final int table , Model model,
            @RequestParam(value = "zoneSearch", required = true) String zoneSearch
    ){
    TaxiTable taxiTable = new TaxiTable();
    taxiTable.setTaxi(table);
    model.addAttribute("taxiTable", taxiTable);
    String tab = String.valueOf(taxiTable.getTaxi());
        Iterable<Personne> chercherPersonne = personneService.chercherPersonne(zoneSearch);
        model.addAttribute("filtre", zoneSearch);
        model.addAttribute("personnes", chercherPersonne);
        return new ModelAndView("redirect:/rechercheFicheReception/" + tab);
    }

//    @PostMapping("/rechercheFicheReception/{filtre}/{table}")
//    public String rechercheFicheReceptionFiltre(@PathVariable("filtre") final String filtre,@PathVariable("table") final int table, @NotNull Model model){
//        Iterable<Personne> chercherPersonne = personneService.chercherPersonne(filtre);
//
//        model.addAttribute("personnes", chercherPersonne);
//        TaxiTable taxiTable = new TaxiTable();
//        taxiTable.setTaxi(table);
//        model.addAttribute("taxiTable" , taxiTable );
//        model.addAttribute("filtre" , filtre);
//        return "rechercheFicheReception";
//
//    }

    @GetMapping("/rechercherFiltre/{table}/{filtre}")
    public ModelAndView rechercherFiltre (
            @PathVariable("table") final int table, Model model, @PathVariable("filtre") String filtre
    ){
        TaxiTable taxiTable = new TaxiTable();
        taxiTable.setTaxi(table);
        model.addAttribute("taxiTable", taxiTable);
        String tab = String.valueOf(taxiTable.getTaxi());
        Iterable<Personne> chercherPersonne = personneService.chercherPersonne(filtre);
        model.addAttribute("filtre", filtre);
        model.addAttribute("personnes", chercherPersonne);
        return new ModelAndView("redirect:/rechercheFicheReception/" + tab);

    }


    }




