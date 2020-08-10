package com.app.repository.impl;

import com.app.connection.DbConnection;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends AbstractCrudRepository<User, Integer> implements UserRepository {

    private final DbConnection dbConnection;


    public UserRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var sql = """
                select * from users where username = :username
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("username", username)
                        .mapToBean(User.class)
                        .findFirst()
                );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var sql = """
                select * from users where email = :email;
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("email", email)
                        .mapToBean(User.class)
                        .findFirst()
                );
    }
}
