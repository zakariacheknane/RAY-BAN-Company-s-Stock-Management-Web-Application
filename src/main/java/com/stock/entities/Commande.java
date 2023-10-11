package com.stock.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles_approvisionnement")
public class Commande {

    @Id
    private Long codeArt;
    
    private Long qteCommande;
    private Date datePrevueLivraison;

    // Getter et Setter pour codeArt
    public Long getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(Long codeArt) {
        this.codeArt = codeArt;
    }

    // Getter et Setter pour qteCommande
    public Long getQteCommande() {
        return qteCommande;
    }

    public void setQteCommande(Long qteCommande) {
        this.qteCommande = qteCommande;
    }

    // Getter et Setter pour datePrevueLivraison
    public Date getDatePrevueLivraison() {
        return datePrevueLivraison;
    }

    public void setDatePrevueLivraison(Date datePrevueLivraison) {
        this.datePrevueLivraison = datePrevueLivraison;
    }
}
