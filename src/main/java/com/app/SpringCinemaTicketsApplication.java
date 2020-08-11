package com.app;

import com.app.connection.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class SpringCinemaTicketsApplication {

    public static void main(String[] args) throws URISyntaxException, SQLException {
/*        var db = new DbConnection();
        db.setUpTables();*/
        getConnection();
        SpringApplication.run(SpringCinemaTicketsApplication.class, args);
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }

}
