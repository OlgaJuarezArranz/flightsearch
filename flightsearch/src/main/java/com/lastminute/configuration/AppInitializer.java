/*
 * flight search
 */

package com.lastminute.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * The content above resembles the content of web.xml as we are using the front-controller DispatherServler, assigning the
 * mapping (url-pattern in xml) and instead of providing the path to spring configuration file(spring-servlet.xml) , here we are
 * registering the Configuration Class.
 *
 * @author ojuarez
 *
 */

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        
    }

}

