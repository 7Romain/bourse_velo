package fr.oz.boursevelo.repository;

import fr.oz.boursevelo.CustomProperties;
import fr.oz.boursevelo.model.Personne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PersonneProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all personnes
     * @return An iterable of all personnes
     */

    public Iterable<Personne> getPersonnes() {
        String baseApiUrl = props.getApiUrl();
        String getPersonnesUrl = baseApiUrl + "/personnes";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Personne>> response = restTemplate.exchange(
                getPersonnesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Personne>>() {}
        );

        log.debug("Get Personnes call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an personne by the id
     * @param id The id of the personne
     * @return The personne which matches the id
     */
    public Personne getPersonne(int id) {
        String baseApiUrl = props.getApiUrl();
        String getPersonneUrl = baseApiUrl + "/personne/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Personne> response = restTemplate.exchange(
                getPersonneUrl,
                HttpMethod.GET,
                null,
                Personne.class
        );

        log.debug("Get Personne call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new personne
     * @param e A new personne (without an id)
     * @return The personne full filled (with an id)
     */
    public Personne createPersonne(Personne e) {
        String baseApiUrl = props.getApiUrl();
        String createPersonneUrl = baseApiUrl + "/personne";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Personne> request = new HttpEntity<Personne>(e);
        ResponseEntity<Personne> response = restTemplate.exchange(
                createPersonneUrl,
                HttpMethod.POST,
                request,
                Personne.class);

        log.debug("Create Personne call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an personne - using the PUT HTTP Method.
     * @param e Existing personne to update
     */
    public Personne updatePersonne(Personne e) {
        String baseApiUrl = props.getApiUrl();
        String updatePersonneUrl = baseApiUrl + "/personne/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Personne> request = new HttpEntity<Personne>(e);
        ResponseEntity<Personne> response = restTemplate.exchange(
                updatePersonneUrl,
                HttpMethod.PUT,
                request,
                Personne.class);

        log.debug("Update Personne call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Delete an personne using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The personne to delete
     */
    public void deletePersonne(Long id) {
        String baseApiUrl = props.getApiUrl();
        String deletePersonneUrl = baseApiUrl + "/personne/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deletePersonneUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Personne call " + response.getStatusCode().toString());
    }






}
