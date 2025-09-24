package com.cp.dasa.config;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class ConnectionFactory {
    // Puxa os dados do application.properties
    String url = System.getenv("ORACLE_URL");
    String user = System.getenv("ORACLE_USER");
    String pass = System.getenv("ORACLE_PASSWORD");

    public Connection getConnection() {
        if (url == null) url = System.getProperty("oracle.url");
        if (user == null) user = System.getProperty("oracle.user");
        if (pass == null) pass = System.getProperty("oracle.password");

        if (url == null || user == null || pass == null) {
            try (var in = ConnectionFactory.class.getResourceAsStream("/application.properties")) {
                if (in != null) {
                    var propsFile = new java.util.Properties();
                    propsFile.load(in);
                    if (url == null) url = propsFile.getProperty("oracle.url");
                    if (user == null) user = propsFile.getProperty("oracle.user");
                    if (pass == null) pass = propsFile.getProperty("oracle.password");

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (url == null || user == null || pass == null) {
            throw new IllegalArgumentException("Configuração Oracle ausente. " +
                    "Defina ORACLE_URL/USER/PASSWORD ou application.properties");
        }

        var props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);
        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}