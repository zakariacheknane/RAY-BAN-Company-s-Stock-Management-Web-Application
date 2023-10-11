package com.stock.managedbeans;

import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stock.entities.Commande;
import com.stock.entities.Product;
import com.stock.services.CommandeService;
import com.stock.services.ProductService;

@Component
@ManagedBean(name = "commandeManagedBean")
@SessionScoped
public class CommandeManagedBean {
    @Autowired
    private CommandeService commandeService;
    @Autowired
    public ProductService productService;
    
    private List<Commande> commandes;
    private Commande selectedCommande;
    private Commande commande;
    
    // Getter et Setter pour selectedCommande
    public Commande getSelectedCommande() {
        return selectedCommande;
    }

    public void setSelectedCommande(Commande selectedCommande) {
        this.selectedCommande = selectedCommande;
    }
    
    // Méthode pour la navigation vers la page de commande
    public String commande() {
        return "commande?faces-redirect=true";
    }
    
    // Méthode pour la navigation vers la page d'ajout de commande
    public String addcommande() {
        return "addcommande?faces-redirect=true";
    }
    
    // Méthode pour afficher les commandes
    public void afficher() {
        this.commande = new Commande();
        this.commandes = commandeService.findAll();
    }
    
    // Méthode pour enregistrer une commande
    public String saveCommande() {
        Product product = new Product();
        // Vérifier si le codeArt existe dans la table Product
        product = productService.find(commande.getCodeArt());
        
        if (product == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Le produit n'existe pas ! Ajoutez-le avant de le commander."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "add?faces-redirect=true";
        } else if (commandeService.find(commande.getCodeArt()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Le produit a déjà été commandé !"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "commande?faces-redirect=true";
        } else if (commande.getQteCommande() != null && commande.getQteCommande().longValue() > 0 && commande.getDatePrevueLivraison() != null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate datePrevueLivraison = commande.getDatePrevueLivraison().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            if (datePrevueLivraison.isAfter(currentDate) || datePrevueLivraison.isEqual(currentDate)) {
                commandeService.create(commande);
                return "Confirmation?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Date invalide"));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Vérifiez les informations de la commande"));
            return "";
        }
    }
    
    // Méthode pour la modification d'une commande
    public String edit(Commande commande) {
        this.commande = commande;
        return "editcommande?faces-redirect=true";
    }
    
    public String update() {
        if (commande.getQteCommande() != null && commande.getQteCommande().longValue() > 0 && commande.getDatePrevueLivraison() != null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate datePrevueLivraison = commande.getDatePrevueLivraison().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            if (datePrevueLivraison.isAfter(currentDate) || datePrevueLivraison.isEqual(currentDate)) {
                this.commandeService.update(this.commande);
                return "commande?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Date invalide"));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement", "Vérifiez les informations de la commande"));
            return "";
        }
    }
    
    // Méthode de test
    public String test() {
        this.commandes = commandeService.findAll();
        LocalDate currentDate = LocalDate.now();
        
        for (Commande commande : commandes) {
            LocalDate datePrevueLivraison = commande.getDatePrevueLivraison()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            
            if (datePrevueLivraison.isEqual(currentDate)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "La date de livraison est égale à la date actuelle. Code Article: " + commande.getCodeArt()));
            }
        }
        
        return "";
    }
    
    // Méthode pour mettre à jour une commande
    public void updateCommande(Commande selectedCommande) {
        LocalDate currentDate = LocalDate.now();
        LocalDate datePrevueLivraison = selectedCommande.getDatePrevueLivraison()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        
        if (datePrevueLivraison.isEqual(currentDate)) {
            // Mettre à jour la quantité du produit
            Product product = productService.find(selectedCommande.getCodeArt());
            if (product != null) {
                Long newQuantity = product.getQteArt() + selectedCommande.getQteCommande();
                product.setQteArt(newQuantity);
                productService.update(product);
                commandeService.delete(selectedCommande);
            }
        }
    }
    
    // Méthode pour supprimer une commande
    public String delete(Commande commande) {
        this.commandeService.delete(commande);
        return "commande?faces-redirect=true";
    }
    
    // Getter et Setter pour commandes
    public List<Commande> getCommandes() {
        return commandes;
    }
    
    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
    
    // Getter et Setter pour commande
    public Commande getCommande() {
        return commande;
    }
    
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    
    // Getter et Setter pour commandeService
    public CommandeService getCommandeService() {
        return commandeService;
    }
    
    public void setCommandeService(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
}
