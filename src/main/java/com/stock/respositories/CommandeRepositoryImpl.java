package com.stock.respositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stock.entities.Commande;

@Repository
@Transactional
public class CommandeRepositoryImpl implements CommandeRepository {
    // Injection de l'EntityManager pour interagir avec la base de données
    @PersistenceContext
    private EntityManager entityManager;

    // Récupère toutes les commandes de la base de données
    @SuppressWarnings("unchecked")
    @Override
    public List<Commande> findAll() {
        try {
            return entityManager.createQuery("From Commande").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    // Récupère une commande par son codeArt
    @Override
    public Commande find(Long codeArt) {
        try {
            return (Commande) entityManager.createQuery("FROM Commande WHERE codeArt = :codeArt")
                    .setParameter("codeArt", Long.valueOf(codeArt)).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Crée une nouvelle commande dans la base de données
    @Override
    public void create(Commande commande) {
        entityManager.persist(commande);
    }

    // Met à jour une commande existante dans la base de données
    @Override
    public void update(Commande commande) {
        entityManager.merge(commande);
    }

    // Supprime une commande de la base de données
    @Override
    public void delete(Commande commande) {
        entityManager.remove(entityManager.merge(commande));
    }
}
