package com.example.vuespringboot.dao;

import com.example.vuespringboot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
