package com.stock.managedbeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

@Component
@ManagedBean(name = "loginManagedBean")
@SessionScoped
public class LoginManagedBean {
    private String nom;
    private String password;

    private boolean loggedIn;
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        // Perform authentication logic here
        if (nom.equals("salman") && password.equals("sliman")) {
            loggedIn = true;
            return "index?faces-redirect=true"; // Redirige vers la page d'accueil après une connexion réussie
        } else if (nom.equals("zakaria") && password.equals("cheknane")) {
            loggedIn = true;
            return "index?faces-redirect=true"; // Redirige vers la page d'accueil après une connexion réussie
        } else if (nom.equals("Niamat Allah") && password.equals("EL OUAZZANI")) {
            loggedIn = true;
            return "index?faces-redirect=true"; // Redirige vers la page d'accueil après une connexion réussie
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null)); // Affiche un message d'erreur en cas de nom d'utilisateur ou de mot de passe incorrect
            return null;
        }
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void checkLoggedIn() {
        if (!loggedIn) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml"); // Redirige vers la page de connexion si l'utilisateur n'est pas connecté
            } catch (IOException e) {
                // Gérer l'exception
            }
        }
    }
    
    public void checkLoginStatus() {
        if (loggedIn) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml"); // Redirige vers la page d'accueil si l'utilisateur est déjà connecté
            } catch (IOException e) {
                // Gérer l'exception
            }
        }
    }
    
    public String logout() {
        loggedIn = false;
        // Effectuer ici la logique de déconnexion si nécessaire
        // Effacer les données de session ou invalider la session
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true"; // Redirige vers la page de connexion après une déconnexion réussie
    }
}
