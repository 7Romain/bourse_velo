package fr.oz.boursevelo.model;

import lombok.Data;

import java.time.LocalDate;


@Data
public class Personne {

    private Long id;

    private String prenom;

    private String nom;

    private String telephone;

    private String commune;

    private LocalDate dateInscription = LocalDate.now();

    private String email;

}
