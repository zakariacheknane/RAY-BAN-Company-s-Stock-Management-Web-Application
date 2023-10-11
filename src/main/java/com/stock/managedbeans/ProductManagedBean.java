package com.stock.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stock.entities.Product;
import com.stock.services.CommandeService;
import com.stock.services.ProductService;

@Component
@ManagedBean(name = "productManagedBean")
@SessionScoped
public class ProductManagedBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    public ProductService productService;
    @Autowired
    public CommandeService commandeService;

    private List<Product> products;
    private Product product;

    private String name;
    private String password;

    public void onload() {
        this.product = new Product();
        this.products = productService.findAll();
    }

    public String add() {
        return "add?faces-redirect=true";
    }

    public String save() {
        // Vérifie si le codeArt existe déjà
        if (productService.find(product.getCodeArt()) != null) {
            // Ajoute un message d'avertissement
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "This product already exists"));
            return null; // Retourne null pour rester sur la même page
        }
        if (product.getCodeArt() == null || product.getNomArt() == null || product.getPrixArt() == null
                || product.getCodeArt() < 0 || product.getPrixArt() <= 0 || product.getQteArt() <0||product.getQteArt() <=0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Make sure all informations are correct"));
            return null; // Retourne null pour rester sur la même page
        }
        if (product.getQteArt() == null) {
            product.setQteArt(0l);
        }

        try {
            productService.create(product);
            return "index?faces-redirect=true";
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Quantity must be a real number"));
            return null; // Retourne null pour rester sur la même page
        }
    }

    public String delete(Product product) {
        if (commandeService.find(product.getCodeArt()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",
                    "Article already ordered! Can't be deleted."));
            return null;
        } else {
            this.productService.delete(product);
            return "index?faces-redirect=true";
        }
    }

    public String edit(Product product) {
        this.product = product;
        return "edit?faces-redirect=true";
    }

    public String update() {
        if (product.getNomArt() == null || product.getPrixArt() == null || product.getPrixArt() <= 0
                || product.getQteArt() < 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Make sure all informations are correct"));
            return null; // Retourne null pour rester sur la même page
        }

        this.productService.update(this.product);
        return "index?faces-redirect=true";
    }

    public String back() {
        return "index?faces-redirect=true";
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
