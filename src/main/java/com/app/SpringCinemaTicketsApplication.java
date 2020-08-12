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
/*        System.out.println("--------------------");
        getConnection();
        System.out.println("--------------------");*/
        SpringApplication.run(SpringCinemaTicketsApplication.class, args);
    }

/*    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = "pw_student";
        String password = "yLr2IT4g3H";
        String dbUrl = "jdbc:mysql://db4free.net:3306/cinema_app_db?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Warsaw";
        return DriverManager.getConnection(dbUrl, username, password);
    }*/

}
