package com.example.vuespringboot;

import com.example.vuespringboot.dao.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
