package fr.oz.boursevelo.repository;

import fr.oz.boursevelo.CustomProperties;
import fr.oz.boursevelo.model.Article;
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
public class ArticleProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Article> getArticles(int id) {
        String baseApiUrl = props.getApiUrl();
        String getArticlesUrl = baseApiUrl + "/articles/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Article>> response = restTemplate.exchange(
                getArticlesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Article>>() {
                }

        );

        log.debug("Get Article call " + response.getStatusCode().toString());

        return response.getBody();

    }

    public Article createArticle(Article e) {
        String baseApiUrl = props.getApiUrl();
        String createArticleUrl = baseApiUrl + "/article";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Article> request = new HttpEntity<Article>(e);
        ResponseEntity<Article> response = restTemplate.exchange(
                createArticleUrl,
                HttpMethod.POST,
                request,
                Article.class);

        log.debug("Create Article call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Article updateArticle(Article e) {
        String baseApiUrl = props.getApiUrl();
        String updateArticleUrl = baseApiUrl + "/article/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Article> request = new HttpEntity<Article>(e);
        ResponseEntity<Article> response = restTemplate.exchange(
                updateArticleUrl,
                HttpMethod.PUT,
                request,
                Article.class);

        log.debug("Update Article call " + response.getStatusCode().toString());

        return response.getBody();
    }

}
