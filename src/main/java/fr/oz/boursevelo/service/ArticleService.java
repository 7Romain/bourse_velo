package fr.oz.boursevelo.service;

import fr.oz.boursevelo.model.Article;
import fr.oz.boursevelo.repository.ArticleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ArticleService {

@Autowired
    private ArticleProxy articleProxy;

public Iterable<Article> getArticles(int id){
    return articleProxy.getArticles(id);
}

public Article saveArticle (Article article){
    Article savedArticle;

    article.setPrixVente(article.calculPrixVente(article.getPrixVendeur()));
    //article.setReference(article.setNumeroTable());
    if(article.getId() == null){
        savedArticle = articleProxy.createArticle(article);
            }else{
        savedArticle = articleProxy.updateArticle(article);

    }
    return savedArticle;
}
}

