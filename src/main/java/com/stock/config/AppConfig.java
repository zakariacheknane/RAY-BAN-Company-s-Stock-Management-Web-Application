package com.stock.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stock.respositories.CommandeRepository;
import com.stock.respositories.CommandeRepositoryImpl;
import com.stock.respositories.ProductRepository;
import com.stock.respositories.ProductRepositoryImpl;
import com.stock.services.CommandeService;
import com.stock.services.CommandeServiceImpl;
import com.stock.services.ProductService;
import com.stock.services.ProductServiceImpl;

@Configuration
@ComponentScan("com.stock")
@EnableTransactionManagement
public class AppConfig {

    // Configuration de l'EntityManager pour la persistance JPA
    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan(new String[] { "com.stock.entities" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setJpaProperties(additionalProperties());
        return entityManager;
    }

    // Configuration du DriverManagerDataSource pour la source de données
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/g_stock");
        dataSource.setUsername("root");
        return dataSource;
    }

    // Configuration du gestionnaire de transaction JPA
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    // Configuration du post-processeur de traduction des exceptions de persistance
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    // Configuration des propriétés additionnelles pour Hibernate
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    // Configuration du repository pour les produits
    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    // Configuration du service pour les produits
    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productRepository());
    }

    // Configuration du repository pour les commandes
    @Bean
    public CommandeRepository commandeRepository() {
        return new CommandeRepositoryImpl();
    }

    // Configuration du service pour les commandes
    @Bean
    public CommandeService commandeService() {
        return new CommandeServiceImpl(commandeRepository());
    }

}
