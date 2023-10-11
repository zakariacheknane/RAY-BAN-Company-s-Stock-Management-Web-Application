package com.stock.respositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.stock.entities.Product;

@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findAll() {
        try {
            // Récupère tous les produits de la base de données en utilisant une requête JPQL
            return entityManager.createQuery("FROM Product").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Product find(Long codeArt) {
        try {
            // Récupère un produit spécifique en utilisant son codeArt comme critère de recherche
            return (Product) entityManager.createQuery("FROM Product WHERE codeArt = :codeArt")
                    .setParameter("codeArt", Long.valueOf(codeArt)).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void create(Product product) {
        // Vérifie si un produit avec le même codeArt existe déjà dans la base de données
        Product existingProduct = find(product.getCodeArt());
        if (existingProduct != null) {
            logger.error("Product with codeArt {} already exists.", product.getCodeArt());
            // Vous pouvez choisir de retourner ou de prendre toute autre action souhaitée ici
            return;
        }
        // Persiste le produit en utilisant l'EntityManager
        entityManager.persist(product);
    }

    @Override
    public void update(Product product) {
        // Met à jour le produit en fusionnant les modifications avec l'EntityManager
        entityManager.merge(product);
    }

    @Override
    public void delete(Product product) {
        // Supprime le produit de la base de données en fusionnant l'instance avec l'EntityManager puis en la supprimant
        entityManager.remove(entityManager.merge(product));
    }
}
