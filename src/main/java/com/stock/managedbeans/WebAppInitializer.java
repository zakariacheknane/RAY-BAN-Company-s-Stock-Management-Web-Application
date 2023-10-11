package com.stock.managedbeans;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.stock.config.AppConfig;

public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        // Crée un contexte d'application Spring basé sur les configurations de la classe AppConfig
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(servletContext);

        // Ajoute un listener pour charger le contexte d'application Spring au démarrage de l'application
        servletContext.addListener(new ContextLoaderListener(ctx));

        // Ajoute un listener pour gérer le contexte de la requête
        servletContext.addListener(new RequestContextListener());

        // Crée et configure le servlet DispatcherServlet pour gérer les requêtes HTTP
        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }
}
