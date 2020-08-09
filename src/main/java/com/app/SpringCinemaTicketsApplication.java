package com.app;

import com.app.connection.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCinemaTicketsApplication {

    public static void main(String[] args) {

        DbConnection db = new DbConnection();
        db.setUpTables();

        SpringApplication.run(SpringCinemaTicketsApplication.class, args);
    }

}
