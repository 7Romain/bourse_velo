package fr.oz.boursevelo.service;

import fr.oz.boursevelo.model.Article;
import fr.oz.boursevelo.repository.ArticleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    int numTable =  article.getTableEnregistrement();
    int nombreArticleRef = ((Collection<?>)getTableCount(numTable)).size();
    int numeroRef = numTable * 1000 + nombreArticleRef;
   // while (!(testRef(numeroRef, articleProxy.getTableCount(numTable)))){
    while (articleProxy.getArticleByRef(numeroRef) != null){

    numeroRef ++;
    }
    article.setReference(numeroRef);
    if(article.getId() == null){
        savedArticle = articleProxy.createArticle(article);
            }else{
        savedArticle = articleProxy.updateArticle(article);

    }
    return savedArticle;
}

    public Iterable<Article> getTableCount(int table){
        return articleProxy.getTableCount(table);
    }

    /**
     * Test si le numéro de référence est présent dans la base
     * @param ref le numéro de référence de l'article
     * @param articles le tableau d'article de la table concerné.
     * @return true si le numéro est unique, false si il y a un doublon.
     */
    public boolean testRef(int ref, Iterable<Article> articles){
    boolean ok = true;
        for (Article article: articles
             ) {
            if (article.getReference() == ref) {
                ok = false;
                break;
            }


        }
    return ok;
    }


}

