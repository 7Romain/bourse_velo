package fr.oz.boursevelo.service;

import fr.oz.boursevelo.model.Personne;
import fr.oz.boursevelo.repository.PersonneProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class PersonneService {
    @Autowired
    private PersonneProxy personneProxy;


    public Personne getPersonne(final int id) {
        return personneProxy.getPersonne(id);
    }

    public Iterable<Personne> getPersonnes() {
        return personneProxy.getPersonnes();
    }

    public Iterable<Personne> chercherPersonne(String info){
        return personneProxy.chercherPersonne(info);
    }

    public void deletePersonne(final long id) {
        personneProxy.deletePersonne(id);
    }

    public Personne savePersonne(Personne personne) {
        Personne savedPersonne;

        // Règle de gestion : Le nom de famille doit être mis en majuscule.
        personne.setNom(personne.getNom().toUpperCase());

        if(personne.getId() == null) {
            // Si l'id est nul, alors c'est un nouvel employé.
            savedPersonne = personneProxy.createPersonne(personne);
        } else {
            savedPersonne = personneProxy.updatePersonne(personne);
        }

        return savedPersonne;
    }}