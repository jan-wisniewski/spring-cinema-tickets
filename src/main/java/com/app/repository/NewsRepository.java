package com.app.repository;

import com.app.model.News;
import com.app.repository.generic.AbstractCrudRepository;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NewsRepository extends CrudRepository<News,Integer> {
}
