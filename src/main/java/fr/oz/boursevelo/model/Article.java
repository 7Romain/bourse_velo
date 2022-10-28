package fr.oz.boursevelo.model;


import fr.oz.boursevelo.service.ReferenceTable;
import lombok.Data;



@Data
public class Article {


    private Long id;

    private Categorie categorie;

    private Type type;

    private String taille;


    private String couleurs;

    private String annotations;

    private int reference;

    private int prixVendeur;

    private int prixVente;

    private long idVendeur;

    private int tableEnregistrement;

    public int calculPrixVente(int prixVendeur){
        double taux = 0.05;
        return  (int)Math.ceil(( prixVendeur  *  taux ) + prixVendeur);
    }

}
