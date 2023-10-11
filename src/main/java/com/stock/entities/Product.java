package com.stock.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles_stock")
public class Product {
    @Id
    private Long codeArt;
    private Long qteArt;
    private String nomArt;
    private String descArt;
    private Long prixArt;

    // Getter et Setter pour codeArt
    public Long getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(Long codeArt) {
        this.codeArt = codeArt;
    }

    // Getter et Setter pour qteArt
    public Long getQteArt() {
        return qteArt;
    }

    public void setQteArt(Long qteArt) {
        this.qteArt = qteArt;
    }

    // Getter et Setter pour nomArt
    public String getNomArt() {
        return nomArt;
    }

    public void setNomArt(String nomArt) {
        this.nomArt = nomArt;
    }

    // Getter et Setter pour descArt
    public String getDescArt() {
        return descArt;
    }

    public void setDescArt(String descArt) {
        this.descArt = descArt;
    }

    // Getter et Setter pour prixArt
    public Long getPrixArt() {
        return prixArt;
    }

    public void setPrixArt(Long prixArt) {
        this.prixArt = prixArt;
    }
}
