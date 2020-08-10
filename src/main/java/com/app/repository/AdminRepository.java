package com.app.repository;

import com.app.model.User;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<User, Integer> {
}
