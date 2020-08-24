package com.app.repository.impl;

import com.app.config.connection.DbConnection;
import com.app.model.News;
import com.app.repository.NewsRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepositoryImpl extends AbstractCrudRepository<News,Integer> implements NewsRepository {

    private DbConnection dbConnection;

    public NewsRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }
}
