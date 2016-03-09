package br.com.org.web.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServerProperties implements ServletContextListener {

    private static final String DEFAULT_NAME = "webapp.properties";
    private static final String PROPERTIES_ENV = "java:comp/env/propertiesPath";
    private static ServerProperties self;

    private final Properties properties;

    public ServerProperties() {
        this.properties = new Properties();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            InputStream propertiesStream = lookupProperties();
            this.properties.load(propertiesStream);
            propertiesStream.close();
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        self = this;
    }

    private InputStream lookupProperties() {
        try {
            String path = (String) new InitialContext().lookup(PROPERTIES_ENV);
            return new FileInputStream(path);
            
        } catch (NamingException e) {
            return this.getClass().getClassLoader().getResourceAsStream(DEFAULT_NAME);
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    public static String property(String name) {
        return self.properties.getProperty(name);
    }
    
    public static void setProperty(String name, String value) {
        self.properties.setProperty(name, value);
    }

}