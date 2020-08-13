package com.app.repository.impl;

import com.app.config.connection.DbConnection;
import com.app.model.User;
import com.app.repository.AdminRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl extends AbstractCrudRepository<User, Integer> implements AdminRepository {
    public AdminRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
    }
}
